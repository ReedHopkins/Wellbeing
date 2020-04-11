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
    nutrientNames = descriptions[i].find_all('li')
    #print(nutrientNames)
    for n in nutrientNames:
        itemInfo += (nutrientNames[0].text.split(":"))
print(itemInfo[1][:-4])
siteTables = soup.find_all("table", {'class':'responsive'})
n = 0
print("success")
names = []
infos = []
amounts = []
for table in siteTables:
    allInfo = soup.find_all("td", {'class':""})
    
    counter = 0
    for i in range(1, 26, 3):
        
        names += allInfo[i].text
        infos += allInfo[i+1].text
        amounts += allInfo[i+2].text
        #print(names[counter] + " : " + infos[counter] + " : " + amounts[counter])
        counter += 1

        #insert_nutrient(curName, curInfo, curAmount)
#print(itemInfo)    
#for i in range(10):
 #   print(itemInfo[i] + ', ' + amounts[i])