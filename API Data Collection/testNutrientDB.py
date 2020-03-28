from pytest_mongodb.plugin import mongo_engine
from pytest import mark
import pymongo
from pymongo import MongoClient

def test_nutrient_insert(mongodb):
    assert mongodb.nutrients.insert_one({'item': 'jam', 'nf_total_fat': 2.50, 'nf_serving_size_qty': 1, 'nf_serving_size_unit':'cup'})
    
def test_nutrient_remove(mongodb):
    assert mongodb.nutrients.delete_one({'item': 'jam'})
    
def test_nutrient_is_there(mongodb):
    assert mongodb.nutrients.find_one({'item': 'jam'})
    
if __name__ == "__main__":
    client = pymongo.MongoClient("mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority")
    mydb = client["wellbeing"]
    test_nutrient_insert(mydb)
    test_nutrient_is_there(mydb)
    test_nutrient_remove(mydb)