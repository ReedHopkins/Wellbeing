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
    
def get_connection():
    client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
    return client
    
    
def get_ingredients(database):
    #ingredients = ['broccoli','extra lean beef', 'strawberries']
    ingredients_db = database["wellbeing"]
    ingredients_collection = ingredients_db["recipes"]
    #Trial with one recipe
    cursor = ingredients_collection.find()[0]['extendedIngredients']
    return cursor
    
def find_items(database, ingredients):
#    for itm in  database.find():
#        print(itm["item"])

    for itm in  database.find():
        if itm["item"] in ingredients:
#            print(itm["item"])
#            print(ingredients)
            ingredients.remove(itm["item"])

    total_price=0.0
    for item in ingredients:
        print(item)
        flag = 0
        if len(item.split()) > 1:
            items = item.split()
            item = ""
            for i in items:
                if(len(item)>0):
                    item += "+"
                item += i
        flag = 0
        soup = get_soup(BASE_URL, "/search/?q="+item)
        span = soup.find('span', {'class':'cat-price-number'})
        script = soup.find('script', {'type':'application/ld+json'})
        if(script == None):
            print("not available: "+item)
        else:
            data = json.loads(script.text)
            span2 = bs4.BeautifulSoup(span.text, "html.parser")
            if '+' in item:
                items1 = item.split('+')
                combined = ""
                for it in items1:
                    combined += it+" "
                item = combined.rstrip()
            insert_to_db(item,data['price'])

#        total_price += data['price']
#    print("total price: "+str(total_price))
    
def insert_to_db(item, price):
    emp_rec1 = {
        "item":item,
        "price":price
        }
    rec_id1 = collection.insert_one(emp_rec1)

BASE_URL = "https://www.heb.com"
conn = get_connection()
mydb = conn["project"]
collection = mydb["hebData"]

ingredients = []
cursor = get_ingredients(conn)
for c in cursor:
    ingredients.append(c['name'])
print(ingredients)
find_items(mydb.hebData, ingredients)
