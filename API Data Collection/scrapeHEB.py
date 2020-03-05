import urllib.request              # to construct url file-like objects
import json                 # to interpret json
import bs4                  # for BeautifulSoup to scrape easier
import re                   # regex parsing html
import csv                  # to write output at csv
import os.path              # to check if file exists for writing output
from datetime import date   # to put date on output
import sys                  # to get command line inputs

def get_soup(base, link):
    url = urllib.request.urlopen(base + link)
    soup = bs4.BeautifulSoup(url.read(), "html.parser")
    return soup
    
ingredients = ['broccoli','extra lean beef','cumin']

BASE_URL = "https://www.heb.com"

total_price=0.0
for item in ingredients:
    if len(item.split()) > 1:
        items = item.split()
        item = ""
        for i in items:
            if(len(item)>0):
                item += "+"
            item += i
    soup = get_soup(BASE_URL, "/search/?q="+item)
    span = soup.find('span', {'class':'cat-price-number'})
    script = soup.find('script', {'type':'application/ld+json'})
    data = json.loads(script.text)
    span2 = bs4.BeautifulSoup(span.text, "html.parser")
    print(item+": "+str(data['price']))
    total_price += data['price']
print("total price: "+str(total_price))
