import requests
from pprint import pprint
from nutritionix import Nutritionix
from pymongo import MongoClient

import json
import string

#Set up client and database
#client = MongoClient('mongodb://localhost:27017/')
client = MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
db = client.wellbeing

allFoods = ("Acai berry", "Adzuki bean", "Almonds", "Amaranth", "Apple", "Apricot", "Artichoke", "Arugula", "Asian pear", "Asparagus", "Avocado", "Banana",
"Basil", "Bean", "Beef", "Beets", "Blackberry", "Blueberry", "Bok choy", "Broccoli", "Brussels sprouts", "Cabbage", "Canola", "Cantaloupe", "Carrots",
"Cashew", "Cauliflower", "Cayenne pepper", "Celeriac", "Celery", "Chamomile","Chard","Cheese","Cherry","Chestnuts","Chicken","Chili pepper","Chinese cabbage",
"Chocolate","Cilantro","Cinnamon","Coconut","Codfish","Collards","Corn","Cranberry","Cumin","Dates","Dragon fruit","Dill","Eggplant","Eggs","Fig","Garlic","Ginger"
,"Grapefruit","Grapes","Hazelnut","Hibiscus","Hickory nut","Honey","Honeydew melon","Horseradish","Kale","Kiwi","Kohlrabi","Kombucha","Lemon","Lemon grass","Lentils","Lettuce"
,"Licorice root","Lilac","Lime","Lotus","Lychee","Mango","Maple","Mustard","Nectarine","Nettle","Noni","Nori seaweed","Nutmeg","Nutritional yeast","Oats","Octopus","Okra","Olive","Onion"
,"Orange","Oregano","Oyster mushroom","Pak choy","Panax ginseng","Parsley","Peaches","Peanuts","Pear","Peas","Pecan","Pepino melon","Pepper","Peppermint","Persimmon","Pineapple","Pine nuts","Pistachio"
,"Plantain","Plum","Pomegranate","Pomelo","Porcini mushrooms","Pork","Portobella mushroom","Potatoes","Pumpkin","Purslane","Quinoa","Radicchio","Radish","Rambutan","Raspberry","Rhubarb","Rice"
,"Rose","Rutabaga","Safflower","Sage","Salmon","Serrano pepper","Serviceberry","Sesame","Shallots","Shiitake mushroom","Soy","Spaghetti squash","Spearmint","Strawberry","Sugar cane","Spinach","Sunflower"
,"Sweet peppers","Tamarillo","Tamarind","Tapioca","Taro","Tatsoi","Tea","Thistle","Thyme","Tomato","Tomatillo","Trout lily","Tuna","Turkey","Turmeric","Turnip","Vanilla","Venison"
,"Wakame","Walnut","Water chestnuts","Watercress","Watermelon","Watermint","Woodland sorrel","Yam","Yerba mate","Yo choy sum","Yuca","Yumberry","Zucchini")

for food in allFoods:
#get all relevant information for items coming up in the query of "pie"
    url = ("https://api.nutritionix.com/v1_1/search/{}adkfjasjdkh?fields=item_name,nf_total_fat,nf_sugars,nf_sodium,nf_cholesterol,nf_protein&appId=973d994f&appKey=e5b17ecd535365487edd736593279f89".format(food))
    headerInfo = {"x-app-id": "973d994f", "x-app-key": "e5b17ecd535365487edd736593279f89"}
    parameters = {"fields":"item_name"}

    #jsonData holds the response as a JSON array
    #asString holds the JSON as a string
    #items is the actual information with all the items that the query returned
    response = requests.get(url = url, headers = headerInfo)
    jsonData = response.json()
    asString = response.text
    items = jsonData["hits"]
    collection = db.nutrients


    for item in items:
    
        #print(item["fields"])
        #print("\n")
        collection.insert_one(item["fields"])

    #  retrieve one instance in collection 

for i in db.nutrients.find({},{"item_name":"apple"}):
    print(i)
#print(db.nutrients.find_one())
