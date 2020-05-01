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

        //Populating lists
        PopulateModelList("ingredient");
        PopulateModelList("recipes");
        PopulateModelList("nutrient");
        
        Collections.sort(ingredientList, new SortIngredientsByName());
        Collections.sort(recipeList, new SortRecipesByName());
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
    
    public void PopulateModelList (String model){
        MongoCollection<Document> col = db.getCollection(model);
        FindIterable<Document> elements = col.find();
        MongoCursor<Document> cursor = elements.iterator();

        try {
            while (cursor.hasNext()) {
                if (model == "ingredient") {
                    ingredientList.add(new Ingredient(cursor.next()));
                } else if (model == "recipes"){
                	recipeList.add(new Recipe(cursor.next()));
                } else{
                    nutrientList.add(new Nutrient(cursor.next()));
                }
            }
        } finally {
            cursor.close();
        }
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
