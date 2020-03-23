import requests
from pymongo import MongoClient
import json
from bson import json_util

#set up client
client = MongoClient('mongodb://localhost:27017/')
#client = MongoClient("mongodb://projectUser:team7@:27017")
db = client.wellbeing

url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate"
querystring = {"targetCalories":"2000","timeFrame":"day"}

headers = {
    'x-rapidapi-host': "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
    'x-rapidapi-key': "dff27d8a10mshd36ff2e451ab7b5p13382ajsnb7bb908e1f89"
    }

response = requests.request("GET", url, headers=headers, params=querystring)
jsonData = response.json()
asString = response.text
items = jsonData["meals"]
collection = db.meals

for item in items:

	print(item)
	print("\n")
	collection.insert_one(item)

#retrieve one instance in collection 
json_formatted_str = json.dumps(db.meals.find_one(), indent=2,default=json_util.default)
print(json_formatted_str)


