package com.jcg.mongodb.servlet;

import org.bson.Document;

public class Ingredient {
    String item;
    String price;
    String unit;

    public Ingredient(Document ingredient){
        item = (String) ingredient.get("item");
        price = (String) ingredient.get("price");
        unit = (String) ingredient.get("unit");
    }

    public String getitem(){
        return item;
    }

    public String getprice(){
        return price;
    }

    public String getunit(){
        return unit;
    }
}
