import requests

url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate"

querystring = {"targetCalories":"2000","timeFrame":"day"}

headers = {
    'x-rapidapi-host': "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
    'x-rapidapi-key': "dff27d8a10mshd36ff2e451ab7b5p13382ajsnb7bb908e1f89"
    }

response = requests.request("GET", url, headers=headers, params=querystring)

print(response.text)