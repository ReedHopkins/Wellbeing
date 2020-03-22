from pprint import pprint
from nutritionix import Nutritionix
from pymongo import MongoClient
import requests
import json
import string

client = MongoClient('mongodb://localhost:27017/')
#client = MongoClient("mongodb://projectUser:team7@:27017")
db = client.wellbeing
#get all relevant information for items coming up in the query of "pie"
url = "https://api.nutritionix.com/v1_1/search/pie?fields=item_name,nf_total_fat,nf_sugars,nf_sodium,nf_cholesterol,nf_protein&appId=973d994f&appKey=e5b17ecd535365487edd736593279f89"
headerInfo = {"x-app-id": "973d994f", "x-app-key": "e5b17ecd535365487edd736593279f89"}
parameters = {"fields":"item_name"}
response = requests.get(url = url, headers = headerInfo)
jsonData = response.json()
asString = response.text
dataDict = json.loads(asString)
items = dataDict["hits"]
collection = db.nutrients


for item in items:
    
    print(item["fields"])
    print("\n")
    collection.insert_one(item["fields"])
    #db.nutrients.insert_one(item["fields"])

print(db.nutrients.find_one())