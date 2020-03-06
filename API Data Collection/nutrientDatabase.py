from pprint import pprint
from nutritionix import Nutritionix
import requests
import json
import string

#get all relevant information for items coming up in the query of "pie"
url = "https://api.nutritionix.com/v1_1/search/pie?fields=item_name&appId=973d994f&appKey=e5b17ecd535365487edd736593279f89"
headerInfo = {"x-app-id": "973d994f", "x-app-key": "e5b17ecd535365487edd736593279f89"}
parameters = {"fields":"item_name"}
response = requests.get(url = url, headers = headerInfo)
jsonData = response.json()
asString = response.text
dataDict = json.loads(asString)

for value in dataDict:
    if value == "hits":
        print(dataDict["hits"])