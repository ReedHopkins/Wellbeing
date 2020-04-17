package com.jcg.mongodb.servlet;

import org.bson.Document;

public class Ingredient {
    String item;
    String price;
    String unit;
    String image;
    List<Document> ingredients;

    public Ingredient(Document ingredient){
        item = (String) ingredient.get("item");
        price = (String) ingredient.get("price");
        unit = (String) ingredient.get("unit");
        nutrients = (List<Document>) recipe.get("nutrients");
        image = (String) ingredient.get("image");
    }

    public Ingredient(){
        item = "error";
        price = "error";
        unit = "error";
        nutrients = "error";
        image = new ArrayList<Document>();
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

    public String getimage() {
        return image;
    }

    public List<Document> getnutrients() {
        return nutrients;
    }
    
    public boolean isMatch(String s) {
    	    	
    	if (item.toLowerCase().contains(s) || 
    			price.toLowerCase().contains(s) || 
    			unit.toLowerCase().contains(s)) {
    		return true;
    	}
    	
    	return false;
    }
}
