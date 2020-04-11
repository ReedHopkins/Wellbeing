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

public class DatabaseSingleton {
    public static DatabaseSingleton instance;
    private static ArrayList<Document> ingredientList = new ArrayList<Document>();
    private static ArrayList<Document> recipeList = new ArrayList<Document>();
    private static ArrayList<Document> nutrientList = new ArrayList<Document>();
    private static MongoClient mongoClient;

    private DatabaseSingleton() throws UnknownHostException {
        String db_name = "wellbeing";
        String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
        mongoClient = new MongoClient(new MongoClientURI(dbURI));
        MongoDatabase db = mongoClient.getDatabase(db_name);

        //Creating ingredient list
        MongoCollection<Document> colIngr = db.getCollection("hebDataFinal");
        FindIterable<Document> elementsIngr = colIngr.find();
        MongoCursor<Document> cursorIngr = elementsIngr.iterator();
        try {
            while (cursorIngr.hasNext()) {
                ingredientList.add(cursorIngr.next());
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
                recipeList.add(cursorRec.next());
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
                nutrientList.add(cursorNut.next());
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

    public static ArrayList<Document> getRecipes(){
        return recipeList;
    }

    public static ArrayList<Document> getIngredients(){
        return ingredientList;
    }

    public static ArrayList<Document> getNutrients(){
        return nutrientList;
    }

    public static MongoClient getConnection(){
        return mongoClient;
    }
}
