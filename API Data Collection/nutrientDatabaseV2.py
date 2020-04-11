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

def get_soup(base, link):
    url = urllib.request.urlopen(base + link)
    soup = bs4.BeautifulSoup(url.read(), "html.parser")
    return soup

def insert_nutrient(name, desc, rda):
    toInsert = {
        "nutrient":name,
        "description":desc,
        "reccommendedDailyIntake":rda
    }
    collection.insert_one(toInsert)


BASE_URL = "https://www.healthline.com/nutrition/micronutrients#types-and-functions"

client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
db = client['wellbeing']
collection = db['nutrients']

url = urllib.request.urlopen(BASE_URL)
soup = bs4.BeautifulSoup(url.read(), "html.parser")
#print(soup.prettify())
descriptions = soup.find_all("ul", {'class' : 'hl-long-line'})
itemInfo = []
for i in range(4):
    #print(descriptions[i])
    nutrientNames = descriptions[i].find_all('li')
    for n in nutrientNames:
        itemInfo += (nutrientNames[0].text.split(":"))
print(itemInfo)
siteTables = soup.find_all("table", {'class':'responsive'})
n = 0
print("success")

for table in siteTables:
    allInfo = soup.find_all("td", {'class':""})
    for i in range(1, 26, 3):
     
        curName = allInfo[i].text
        curInfo = allInfo[i+1].text
        curAmount = allInfo[i+2].text
        #print(curName + " : " + curInfo + " : " + curAmount)

        #insert_nutrient(curName, curInfo, curAmount)
    