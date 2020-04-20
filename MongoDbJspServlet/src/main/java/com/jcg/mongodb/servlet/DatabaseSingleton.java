package com.jcg.mongodb.servlet;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseSingleton {
    public static DatabaseSingleton instance;
    private static ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
    private static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    private static ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
    private static MongoClient mongoClient;
    private static MongoDatabase db;

    private DatabaseSingleton() throws UnknownHostException {
        String db_name = "wellbeing";
        String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
        mongoClient = new MongoClient(new MongoClientURI(dbURI));
        db = mongoClient.getDatabase(db_name);

        //Creating ingredient list
        MongoCollection<Document> colIngr = db.getCollection("hebDataFinal2");
        FindIterable<Document> elementsIngr = colIngr.find();
        MongoCursor<Document> cursorIngr = elementsIngr.iterator();
        try {
            while (cursorIngr.hasNext()) {
                ingredientList.add(new Ingredient(cursorIngr.next()));
            }
        } finally {
            cursorIngr.close();
        }

        //Creating recipe list
        MongoCollection<Document> colRec = db.getCollection("recipes");
        FindIterable<Document> elementsRec = colRec.find();
        MongoCursor<Document> cursorRec = elementsRec.iterator();
        try {
            while (cursorRec.hasNext()) {
                recipeList.add(new Recipe(cursorRec.next()));
            }
        } finally {
            cursorRec.close();
        }

        //Creating nutrient list
        MongoCollection<Document> colNut = db.getCollection("nutrients");
        FindIterable<Document> elementsNut = colNut.find();
        MongoCursor<Document> cursorNut = elementsNut.iterator();
        try {
            while (cursorNut.hasNext()) {
                nutrientList.add(new Nutrient(cursorNut.next()));
            }
        } finally {
            cursorNut.close();
        }
        mongoClient.close();
    }

    public static DatabaseSingleton getInstance() throws UnknownHostException{
        if(DatabaseSingleton.instance == null){
            DatabaseSingleton.instance = new DatabaseSingleton();
        }
        return DatabaseSingleton.instance;
    }

    public static ArrayList<Ingredient> getIngredients(){
        return ingredientList;
    }
    
    public static ArrayList<Recipe> getRecipes(){
        return recipeList;
    }

    public static ArrayList<Nutrient> getNutrients(){
        return nutrientList;
    }
    
    public static ArrayList<Ingredient> searchIngredients(String s){
    	ArrayList<Ingredient> output = new ArrayList<Ingredient>();
    	String[] s_split = s.split(" ");
    	for (Ingredient i: ingredientList) {
    		for (String elem: s_split) {
    			if (i.isMatch(elem)) {
        			output.add(i);
        		}
    		}
    		
    	}
        return output;
    }
    
    public static ArrayList<Recipe> searchRecipes(String s){
    	ArrayList<Recipe> output = new ArrayList<Recipe>();
    	String[] s_split = s.split(" ");
    	for (Recipe i: recipeList) {
    		for (String elem: s_split) {
    			if (i.isMatch(elem)) {
        			output.add(i);
        		}
    		}
    		
    	}
        return output;
    }
    
    public static ArrayList<Nutrient> searchNutrients(String s){
    	ArrayList<Nutrient> output = new ArrayList<Nutrient>();
    	String[] s_split = s.split(" ");
    	for (Nutrient i: nutrientList) {
    		for (String elem: s_split) {
    			if (i.isMatch(elem)) {
        			output.add(i);
        		}
    		}
    		
    	}
        return output;
    }
    
    public static ArrayList<Ingredient> getSortedIngredients(String sort) {
    	ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(ingredientList);
    	
    	if (sort.equals("atoz") || sort.equals("ztoa")) {
			Collections.sort(ingredients, new SortIngredientsByName());
			if (sort.contentEquals("ztoa")) {
				Collections.reverse(ingredients);
			}
		} else if (sort.equals("lowtohigh") || sort.contentEquals("hightolow")) {
			Collections.sort(ingredients, new SortIngredientsByPrice());
			if (sort.contentEquals("hightolow")) {
				Collections.reverse(ingredients);
			}
		}
    	return ingredients;
    }
    
    public static ArrayList<Recipe> getSortedRecipes(String sort) {
    	ArrayList<Recipe> recipes = new ArrayList<Recipe>(recipeList);
    	
    	if (sort.equals("atoz") || sort.equals("ztoa")) {
			Collections.sort(recipes, new SortRecipesByName());
			if (sort.contentEquals("ztoa")) {
				Collections.reverse(recipes);
			}
		} else if (sort.equals("timelowtohigh") || sort.contentEquals("timehightolow")) {
			Collections.sort(recipes, new SortRecipesByTime());
			if (sort.contentEquals("timehightolow")) {
				Collections.reverse(recipes);
			}
		} else if (sort.equals("servingslowtohigh") || sort.contentEquals("servingshightolow")) {
			Collections.sort(recipes, new SortRecipesByServings());
			if (sort.contentEquals("servingshightolow")) {
				Collections.reverse(recipes);
			}
		} else if (sort.equals("healthlowtohigh") || sort.contentEquals("healthhightolow")) {
			Collections.sort(recipes, new SortRecipesByHealth());
			if (sort.contentEquals("healthhightolow")) {
				Collections.reverse(recipes);
			}
		}
    	return recipes;
    }

    public static MongoClient getConnection(){
        return mongoClient;
    }
}
