import requests
from pymongo import MongoClient
import json
from bson import json_util

#set up client
client = MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
#client = MongoClient("mongodb://projectUser:team7@:27017")
#client = MongoClient('mongodb://localhost:27017/')

db = client.wellbeing

for i in range(4):
	url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random"
	querystring = {"number":"100"}

	headers = {
	    'x-rapidapi-host': "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
	    'x-rapidapi-key': "dff27d8a10mshd36ff2e451ab7b5p13382ajsnb7bb908e1f89"
	    }

	response = requests.request("GET", url, headers=headers, params=querystring)
	jsonData = response.json()
	asString = response.text
	items = jsonData["recipes"]
	collection = db.recipes

	for item in items:

		#print(item)
		#print("\n")
		collection.insert_one(item)

#retrieve one instance in collection 
for i in db.recipes.find({},{"vegetarian":"true"}):
	print(i)
#json_formatted_str = json.dumps(db.meals.find_one(), indent=2,default=json_util.default)
#print(json_formatted_str)

