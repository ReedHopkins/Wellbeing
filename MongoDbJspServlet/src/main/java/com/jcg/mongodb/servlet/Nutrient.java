package com.jcg.mongodb.servlet;

import java.util.Comparator;
import java.util.regex.Pattern;

import org.bson.Document;

public class Nutrient {
    String title;
    String description;
    String dailyIntake;
    String pictureURL;

    public Nutrient(Document nutrientDoc){
        title = (String) nutrientDoc.get("title");
        description = (String) nutrientDoc.get("description");
        dailyIntake = ((String) nutrientDoc.get("reccommendedDailyIntake")).trim();
        pictureURL = (String) nutrientDoc.get("pictureURL");
        
        dailyIntake = dailyIntake.replace("-", "&ndash;");
        dailyIntake = dailyIntake.replace("–", "&ndash;");
    }

    public Nutrient(){
        title = "error";
        description = "error";
        dailyIntake = "error";
        pictureURL =  "error";
    }

    public String gettitle(){
        return title;
    }

    public String getdescription(){
        return description;
    }

    public String getdailyIntake(){
        return dailyIntake;
    }

    public String getpictureURL(){
        return pictureURL;
    }
    
    public boolean isMatch(String s) {
    	
    	if (title.toLowerCase().contains(s) || 
    			description.toLowerCase().contains(s) || 
    			dailyIntake.toLowerCase().contains(s)) {
    		return true;
    	}
    	
    	return false;
    }
}

class SortNutrientsByName implements Comparator<Nutrient> { 
    // Used for sorting in ascending order of name
    public int compare(Nutrient a, Nutrient b) 
    { 
        return a.title.toLowerCase().compareTo(b.title.toLowerCase()); 
    } 
}

class SortNutrientsByIntake implements Comparator<Nutrient> { 
    // Used for sorting in ascending order of name
    public int compare(Nutrient a, Nutrient b) 
    { 
    	String compA = a.dailyIntake.split("&ndash;")[0];
    	String compB = b.dailyIntake.split("&ndash;")[0];
    	compA = compA.split(" ")[0];
    	compB = compB.split(" ")[0];
    	compA = compA.replaceAll("[^\\d.]", "");
    	compB = compB.replaceAll("[^\\d.]", "");
    	
    	if (compA.isEmpty()) return -1;
    	if (compB.isEmpty()) return 1;
    	
    	if (Double.parseDouble(compA) < Double.parseDouble(compB)) return -1;
        if (Double.parseDouble(compA) > Double.parseDouble(compB)) return 1;
        return 0;
    } 
}