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
    #cursor = ingredients_collection.find()[0]['extendedIngredients']
    cursorArray = []
    for element in ingredients_collection.find():
        cursorArray.append(element['extendedIngredients'])
    return cursorArray
    
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
        span = soup.find('span', {'class':'uomSalePrice'})
        script = soup.find('script', {'type':'application/ld+json'})
        if span == None:
            print("no span - skipping - for ingredient: ",item)
            continue
        else:
            priceUnit = span.text.strip().strip('(').strip(')').strip('$').split("/")
            price = priceUnit[0]
            unit = priceUnit[1]
        if(script == None):
            print("not available: "+item)
        else:
            data = json.loads(script.text)
            if '+' in item:
                items1 = item.split('+')
                combined = ""
                for it in items1:
                    combined += it+" "
                item = combined.rstrip()
            insert_to_db(database,item,price,unit)

#        total_price += data['price']
#    print("total price: "+str(total_price))
    
def insert_to_db(db,item, price,unit):
    if db.find({"item":item}).count() == 0:
        emp_rec1 = {
                "item":item,
                "price":price,
                "unit":unit
                }
        collection.insert(
           {"item": item,"price":price,"unit":unit}
        )
    else:
        print("This item is already in the db")
    #rec_id1 = collection.update_one(emp_rec1)

BASE_URL = "https://www.heb.com"
conn = get_connection()
mydb = conn["wellbeing"]
print(mydb.collection_names())
collection = mydb["hebDataFinal"]

ingredients = []
cursorA = get_ingredients(conn)
num = 0
for elem in cursorA:
    print("recipe "+str(num))
    for c in elem:
        ingredients.append(c['name'])
    print(ingredients)
    find_items(mydb.hebDataFinal, ingredients)
    num += 1
