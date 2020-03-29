from pytest_mongodb.plugin import mongo_engine
from pytest import mark
import pymongo
from pymongo import MongoClient

def test_heb_insert(mongodb):
    assert mongodb.hebData.insert_one({'item': 'jelly', 'price': 2.50})
    
def test_heb_remove(mongodb):
    assert mongodb.hebData.delete_one({'item': 'jelly'})
    
def test_heb_is_there(mongodb):
    assert mongodb.hebData.find_one({'item': 'jelly'})

@mark.skipif(mongo_engine() == 'mongomock', reason="mongomock does not support that")
def test_heb(mongodb):
    assert 'hebData' in mongodb.list_collection_names()
    food = mongodb.hebData.find_one({'item': 'oatmeal'})
    assert food['price'] == 2.58
    
if __name__ == "__main__":
    client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
    mydb = client["wellbeing"]
    test_heb(mydb)
    test_heb_insert(mydb)
    test_heb_is_there(mydb)
    test_heb_remove(mydb)
