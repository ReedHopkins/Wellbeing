import requests
from pymongo import MongoClient
import json
from bson import json_util

#set up client
client = MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")

db = client.wellbeing
collection = db.recipes

headers = {
    'x-rapidapi-host': "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
    'x-rapidapi-key': "dff27d8a10mshd36ff2e451ab7b5p13382ajsnb7bb908e1f89"
    }

cursor = collection.find({})
for document in cursor:
	jsonRaw = json_util.dumps(document) # converts bson into json string
	parsedJson = json.loads(jsonRaw) # converts json string into Json object
	id = parsedJson["id"]

	url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"+ str(id) + "/nutritionWidget.json"
	response = requests.request("GET", url, headers=headers)
	jsonData = response.json()

	query = {"id" : id}
	collection.update_one(query, {"$set": jsonData})

#checking if update went through
# myquery = { "id": id }
# mydoc = collection.find(myquery)

# for x in mydoc:
# 	print(x)

print("Success!")
