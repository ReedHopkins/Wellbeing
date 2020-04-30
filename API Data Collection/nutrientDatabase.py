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
        "image":pic,
        "tags":tags,
        "medicalInfo":medical
    }
    collection.insert_one(toInsert)

client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
db = client['wellbeing']
collection = db['nutrients']

URL2 = "https://www.innerbody.com/nutrition/macronutrients"
IMAGE_URL = "https://shutterstock.com/search/"

url = urllib.request.urlopen(URL2)
soup = bs4.BeautifulSoup(url.read(), "html.parser")

names = soup.find_all("strong", {'class' : ''})



descriptions = []
for i in range(2):
    d = names[i+2].next_sibling
    if (d[0] == ","):
        d = d[1:]
    descriptions.append(d)

    url = urllib.request.urlopen(IMAGE_URL+(names[i+2].text))
    soup2 = bs4.BeautifulSoup(url.read(),"html.parser")
    searchResults = soup2.find("script",{'type':'application/ld+json'})
    dictResults = json.loads(((searchResults.text)))
    imgURL = dictResults[0]["contentUrl"]
    insert_nutrient(names[i+2].text, d, "See carbohydrates", imgURL, ["macronutrient"], "See carbohydrates")

transFat = []
transFat.append("Trans Fat")
transFat.append("These partially hydrogenated trans fats can increase total blood cholesterol, LDL cholesterol and triglyceride levels, but lower HDL cholesterol")
transFat.append("Try to avoid")
transFat.append("If trans fat is a big part of your diet, you should do your best to cut down on your intake.")
url = urllib.request.urlopen(IMAGE_URL+("Trans Fat"))
soup2 = bs4.BeautifulSoup(url.read(),"html.parser")
searchResults = soup2.find("script",{'type':'application/ld+json'})
dictResults = json.loads(((searchResults.text)))
imgURL = dictResults[0]["contentUrl"]
insert_nutrient(transFat[0], transFat[1], transFat[2], imgURL, ["macronutrient"], transFat[3])

satFat = []
satFat.append("Saturated Fat")
satFat.append("raise high-density lipoprotein cholesterol and low-density lipoprotein cholesterol levels, which may increase your risk of cardiovascular disease")
satFat.append("Less than 10 percent of calories")
satFat.append("If trans fat is a big part of your diet, you should do your best to cut down on your intake.")
url = urllib.request.urlopen(IMAGE_URL+("Saturated Fat"))
soup2 = bs4.BeautifulSoup(url.read(),"html.parser")
searchResults = soup2.find("script",{'type':'application/ld+json'})
dictResults = json.loads(((searchResults.text)))
imgURL = dictResults[0]["contentUrl"]
insert_nutrient(satFat[0], satFat[1], satFat[2], imgURL, ["macronutrient"], satFat[3])

unsatFat = []
satFat.append("Unsaturated Fatty Acids")
satFat.append("considered beneficial fats because they can improve blood cholesterol levels, ease inflammation, stabilize heart rhythms, and play a number of other beneficial roles")
satFat.append("Should be most of your fat intake (see Fat)")
satFat.append("Vitamins won't help here, but make sure your diet contains unsaturated fats rather than saturated fats.")
url = urllib.request.urlopen(IMAGE_URL+("Unsaturated Fat"))
soup2 = bs4.BeautifulSoup(url.read(),"html.parser")
searchResults = soup2.find("script",{'type':'application/ld+json'})
dictResults = json.loads(((searchResults.text)))
imgURL = dictResults[0]["contentUrl"]
insert_nutrient(satFat[0], satFat[1], satFat[2], imgURL, ["macronutrient"], satFat[3])
