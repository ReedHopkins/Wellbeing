from pytest_mongodb.plugin import mongo_engine
from pytest import mark
import pymongo
from pymongo import MongoClient

DOCUMENT = {
    "_id":{"$oid":"5e7aa1c3efc0b37ba9230c14"},
    "vegetarian":True,
    "vegan":False,
    "glutenFree":True,
    "dairyFree":False,
    "veryHealthy":False,
    "cheap":False,
    "veryPopular":False,
    "sustainable":False,
    "weightWatcherSmartPoints":{"$numberInt":"13"},
    "gaps":"no","lowFodmap":False,
    }

def test_recipe_insert(mongodb):
    assert mongodb.recipe.insert_one(DOCUMENT)
    
def test_recipe_remove(mongodb):
    assert mongodb.recipe.delete_one(DOCUMENT)
    
def test_recipe_is_there(mongodb):
    assert mongodb.recipe.find_one(DOCUMENT)

@mark.skipif(mongo_engine() == 'mongomock', reason="mongomock does not support that")
def test_recipe(mongodb):
    assert 'recipe' in mongodb.list_collection_names()
    food = mongodb.recipe.find_one(DOCUMENT)
    assert food['cheap'] == False
    
if __name__ == "__main__":
    client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
    mydb = client["wellbeing"]
    test_recipe(mydb)
    test_recipe_insert(mydb)
    test_recipe_is_there(mydb)
    test_recipe_remove(mydb)