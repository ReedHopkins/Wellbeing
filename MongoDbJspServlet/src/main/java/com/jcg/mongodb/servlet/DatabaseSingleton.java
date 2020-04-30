package com.jcg.mongodb.servlet;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
    private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    private ArrayList<Nutrient> nutrientList = new ArrayList<Nutrient>();
    private MongoClient mongoClient;
    private MongoDatabase db;

    private DatabaseSingleton(){
        String db_name = "wellbeing";
        String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
        mongoClient = new MongoClient(new MongoClientURI(dbURI));
        db = mongoClient.getDatabase(db_name);

        //Creating ingredient list
        MongoCollection<Document> colIngr = db.getCollection("ingredients");
        FindIterable<Document> elementsIngr = colIngr.find();
        MongoCursor<Document> cursorIngr = elementsIngr.iterator();
        try {
            while (cursorIngr.hasNext()) {
                ingredientList.add(new Ingredient(cursorIngr.next()));
            }
        } finally {
            cursorIngr.close();
        }
        Collections.sort(ingredientList, new SortIngredientsByName());

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
        Collections.sort(recipeList, new SortRecipesByName());

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
        Collections.sort(nutrientList, new SortNutrientsByName());
        mongoClient.close();
        DatabaseUtility.initializeMaps(ingredientList, recipeList, nutrientList);
    }

    public static DatabaseSingleton getInstance(){
        if(DatabaseSingleton.instance == null){
            DatabaseSingleton.instance = new DatabaseSingleton();
        }
        return DatabaseSingleton.instance;
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredientList;
    }
    
    public ArrayList<Recipe> getRecipes(){
        return recipeList;
    }

    public ArrayList<Nutrient> getNutrients(){
        return nutrientList;
    }

}
