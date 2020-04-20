package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;

public class Ingredient {
    String item;
    String price;
    String unit;
    String image;
    List<Document> nutrients;

    public Ingredient(Document ingredient){
        item = (String) ingredient.get("item");
        price = (String) ingredient.get("price");
        unit = (String) ingredient.get("unit");
        nutrients = (List<Document>) ingredient.get("nutrients");
        image = (String) ingredient.get("image");
    }

    public Ingredient(){
        item = "error";
        price = "error";
        unit = "error";
        image = "error";
        nutrients = new ArrayList<Document>();
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

class SortIngredientsByName implements Comparator<Ingredient> { 
    // Used for sorting in ascending order of name
    public int compare(Ingredient a, Ingredient b) 
    { 
        return a.item.toLowerCase().compareTo(b.item.toLowerCase()); 
    } 
}

class SortIngredientsByPrice implements Comparator<Ingredient> { 
    // Used for sorting in ascending order of name
    public int compare(Ingredient a, Ingredient b) 
    { 
        if (Double.parseDouble(a.price) < Double.parseDouble(b.price)) return -1;
        if (Double.parseDouble(a.price) > Double.parseDouble(b.price)) return 1;
        return 0;
    } 
}
