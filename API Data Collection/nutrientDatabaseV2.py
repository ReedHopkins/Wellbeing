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


def insert_nutrient(name, desc, rda, pic):
    toInsert = {
        "title":name,
        "description":desc,
        "reccommendedDailyIntake":rda,
        "pictureURL":pic
    }
    collection.insert_one(toInsert)


BASE_URL = "https://www.healthline.com/nutrition/micronutrients#types-and-functions"
MACRO_URL = "https://mybodymykitchen.com/calculate-your-macronutrients-protein-fats-carbs/"
GOOGLE_URL = "https://www.google.com/search?tbm=isch&q="
IMAGE_URL = "https://shutterstock.com/search/"

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
    

for i in range(27):
    print(names[i] + ', ' + itemInfo[2*i+1] + ', ' + amounts[i] + ', ' + images[i])
    insert_nutrient(names[i], itemInfo[2*i + 1], amounts[i], images[i])


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
three = {0,1,2}
for n in three:
    curName = headings[n].text
    url = urllib.request.urlopen(IMAGE_URL+(curName)+"nutrient")
    soup = bs4.BeautifulSoup(url.read(),"html.parser")
    searchResults = soup.find("script",{'type':'application/ld+json'})
    dictResults = json.loads(((searchResults.text)))
    imgURL = dictResults[0]["contentUrl"]

    images.append(imgURL)

for i in range(3):
    insert_nutrient(headings[i].text, descriptions[i+2].text, finalDesc[i], images[i])

