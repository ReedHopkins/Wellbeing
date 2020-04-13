package com.jcg.mongodb.servlet;

import org.bson.Document;

public class Recipe {
    String title;
    String readyInMinutes;
    String servings;
    String healthScore;
    String image;

    public Recipe(Document recipe){
        title = (String) recipe.get("title");
        readyInMinutes = Integer.toString((Integer) recipe.get("readyInMinutes"));
        servings = Integer.toString((Integer) recipe.get("servings"));
        healthScore = Double.toString((Double)recipe.get("healthScore"));
        image = (String) recipe.get("image");
    }

    public Recipe(){
        title = "error";
        readyInMinutes = "error";
        servings = "error";
        healthScore = "error";
        image = "error";
    }

    public String gettitle(){
        return title;
    }

    public String getreadyInMinutes(){
        return readyInMinutes;
    }

    public String getservings(){
        return servings;
    }

    public String gethealthScore(){
        return healthScore;
    }

    public String getimage() {
        return image;
    }
    
    public boolean isMatch(String s) {
    	
    	if (title.toLowerCase().contains(s) || 
    			readyInMinutes.toLowerCase().contains(s) || 
    			servings.toLowerCase().contains(s) ||
    			healthScore.toLowerCase().contains(s) ||
    			image.toLowerCase().contains(s)) {
    		return true;
    	}
    	
    	return false;
    }
}
