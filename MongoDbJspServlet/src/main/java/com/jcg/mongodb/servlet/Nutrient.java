package com.jcg.mongodb.servlet;

import org.bson.Document;

public class Nutrient {
    String title;
    String description;
    String dailyIntake;
    String pictureURL;

    public Nutrient(Document nutrientDoc){
        title = (String) nutrientDoc.get("title");
        description = (String) nutrientDoc.get("description");
        dailyIntake = (String) nutrientDoc.get("reccommendedDailyIntake");
        pictureURL = (String) nutrientDoc.get("pictureURL");
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
