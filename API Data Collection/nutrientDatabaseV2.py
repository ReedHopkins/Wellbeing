import urllib.request              # to construct url file-like objects
import json                 # to interpret json
import bs4                  # for BeautifulSoup to scrape easier
import re                   # regex parsing html
import csv                  # to write output at csv
import os.path              # to check if file exists for writing output
from datetime import date   # to put date on output
import sys                  # to get command line inputs
import pymongo
from pymongo import MongoClient


def insert_nutrient(name, desc, rda, pic, tags, medical):
    toInsert = {
        "title":name,
        "description":desc,
        "reccommendedDailyIntake":rda,
        "pictureURL":pic,
        "tags":tags,
        "medicalInfo":medical
    }
    collection.insert_one(toInsert)


BASE_URL = "https://www.healthline.com/nutrition/micronutrients#types-and-functions"
MACRO_URL = "https://mybodymykitchen.com/calculate-your-macronutrients-protein-fats-carbs/"
IMAGE_URL = "https://shutterstock.com/search/"
SUB_MACRO_URL = "https://avitahealth.org/health-library/macronutrients-a-simple-guide-to-macros/"
SUPPLEMENT_URL = "https://www.bodybuilding.com/content/stacked-your-guide-to-supplement-dosage-and-timing.html"

client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
db = client['wellbeing']
collection = db['nutrients']

url = urllib.request.urlopen(BASE_URL)
soup = bs4.BeautifulSoup(url.read(), "html.parser")
#print(soup.prettify())
descriptions = soup.find_all("ul", {'class' : 'hl-long-line'})
itemInfo = []
for i in range(4):
    nutrientNames = descriptions[i].find_all('li')
    #print(nutrientNames)
    for n in nutrientNames:
        itemInfo += (n.text.split(":"))

siteTables = soup.find_all("table", {'class':'responsive'})
n = 0
names = []
infos = []
amounts = []
#for table in siteTables:
allInfo = soup.find_all("td", {'class':""})


for i in range(1, 26, 3):
        
    names += allInfo[i]
    infos += allInfo[i+1]
    amounts += allInfo[i+2]
    
for i in range(29, 40, 3):
        
    names += allInfo[i]
    infos += allInfo[i+1]
    amounts += allInfo[i+2]

for i in range(42, 62, 3):
        
    names += allInfo[i]
    infos += allInfo[i+1]
    amounts += allInfo[i+2]
    
for i in range(64, 84, 3):
        
    names += allInfo[i]
    infos += allInfo[i+1]
    amounts += allInfo[i+2]

images = []

for n in names:

    url = urllib.request.urlopen(IMAGE_URL+n.replace(" ", "+")+"+nutrient")
    soup = bs4.BeautifulSoup(url.read(),"html.parser")
    searchResults = soup.find("script",{'type':'application/ld+json'})
    dictResults = json.loads(((searchResults.text)))
    imgURL = dictResults[0]["contentUrl"]
    print(imgURL)
    images.append(imgURL)
    
medicalInfo = "This nutrient is essential to your health, and if you're not getting the recommended amount, you should take vitamins to do so."
for i in range(27):
    print(names[i] + ', ' + itemInfo[2*i+1] + ', ' + amounts[i] + ', ' + images[i])
    tag = "mineral"
    if("vitamin" in names[i]):
        tag = "vitamin"
    if("-" in amounts[i]):
        amounts[i].replace("-","&ndash;")
    insert_nutrient(names[i], itemInfo[2*i + 1].split("(")[0], amounts[i], images[i], ["micronutrient", tag], medicalInfo)


#Start of accesssing Macronutrients 

url = urllib.request.urlopen(MACRO_URL)
soup = bs4.BeautifulSoup(url.read(), "html.parser")

headings = soup.find_all("strong",{'class':''})
descriptions = soup.find_all("em", {'class':''})
finalDesc = []
finalDesc+=descriptions[6]
for i in range(9,12,2):
    finalDesc+=(descriptions[i])
count = 0
for test in finalDesc:
    finalDesc[count] = test.split("=")[2]
    count+=1

images = []

for n in range(3):
    curName = headings[n].text
    url = urllib.request.urlopen(IMAGE_URL+(curName)+"nutrient")
    soup = bs4.BeautifulSoup(url.read(),"html.parser")
    searchResults = soup.find("script",{'type':'application/ld+json'})
    dictResults = json.loads(((searchResults.text)))
    imgURL = dictResults[0]["contentUrl"]

    images.append(imgURL)

medicalInfo = "This is a major part of your diet, and since supplements aren't the ideal solution, you should look to add more of it in your diet. "
medicalInfo += "See the ingredients database to find foods with this nutrient."
for i in range(3):
    #print(headings[i].text[:-1] + ", " + descriptions[i+2].text + ", " + finalDesc[(i+2)%3])
    insert_nutrient(headings[i].text[:-1], descriptions[i+2].text, finalDesc[(i+2)%3], images[i], ["macronutrient"], medicalInfo)


#Start of accessing workout supplements

url = urllib.request.urlopen(SUPPLEMENT_URL)
soup = bs4.BeautifulSoup(url.read(), "html.parser")

listItems = soup.find_all("h4", {"class":""})
itemAmounts = soup.find_all("li", {"class":""})
for amount in itemAmounts:
    if (not (amount == None) and "-" in amount):
        amount = amount.replace("-","&ndash;")
descriptions = soup.find_all("p",{"class":""})
finalDesc = []

#for item in listItems:
for desc in descriptions:
        #print(item.text + " " + desc.text)
    line = desc.find("a", {"class":""})
    if not(line == None) and not("BCAA" in line.text) and not("pre-workout" in line.text) and not("nitric" in line.text):
    #if not(line == None):
        #print(line)
        finalDesc.append(line.next_sibling)
        if("Citrulline" in line.text):
            finalDesc.append("suggested in numerous studies to enhance aerobic performance, delayed fatigue, and an increase in exercise tolerance")
        if("Glutamine" in line.text):
            finalDesc.append("key when it comes to regulating protein metabolism, promoting protein synthesis, and suppressing protein degradation")    
    #if not(desc == None):
     #   print(desc.text.split(",")[0])

images = []
for n in range(15):
    print(listItems[n])
    curName = listItems[n].text
    if(curName == "Citrulline malate"):
        curName = "Citrulline"
    if(curName == "Beta-hydroxy-B-methylbutyrate (HMB)"):
        curName = "HMB"
    url = urllib.request.urlopen(IMAGE_URL+(curName))
    soup = bs4.BeautifulSoup(url.read(),"html.parser")
    searchResults = soup.find("script",{'type':'application/ld+json'})
    dictResults = json.loads(((searchResults.text)))
    imgURL = dictResults[0]["contentUrl"]

    images.append(imgURL)

for i in range(15):
    #print(listItems[i].text + ": " + finalDesc[i + 1].split(".")[0] + " - " + itemAmounts[i+182].text.split(":")[1])
    curDesc = finalDesc[i+1].split(".")[0]
    if(curDesc[0] == ","):
        curDesc = curDesc[1:]
    medicalInfo = "This is just a workout supplement, so while it will help your body, you don't have to worry about health issues involved with not getting enough!"
    insert_nutrient(listItems[i].text, curDesc, (itemAmounts[i+182].text.split(":")[1].split("g")[0]) + "g",images[i], ["workout suppliment"], medicalInfo)
    