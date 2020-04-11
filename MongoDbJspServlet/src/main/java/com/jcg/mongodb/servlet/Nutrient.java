package com.jcg.mongodb.servlet;

import org.bson.Document;

public class Nutrient {
    String nutrient;
    String description;
    String dailyIntake;

    public Nutrient(Document nutrientDoc){
        nutrient = (String) nutrientDoc.get("nutrient");
        description = (String) nutrientDoc.get("description");
        dailyIntake = (String) nutrientDoc.get("reccommendedDailyIntake");
    }

    public String getnutrient(){
        return nutrient;
    }

    public String getdescription(){
        return description;
    }

    public String getdailyIntake(){
        return dailyIntake;
    }
}
