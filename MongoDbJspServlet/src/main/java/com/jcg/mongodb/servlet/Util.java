package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Util {

	private static ArrayList<Document> ingredientList = new ArrayList<Document>();
	private static ArrayList<Document> recipeList = new ArrayList<Document>();
	private static ArrayList<Document> nutrientList = new ArrayList<Document>();
	private static MongoClient mongoClient;

	// Method to make a connection to the mongodb server listening on a default port
	private static void getConnection() {
		String db_name = "wellbeing";
		
		if (mongoClient == null) {
			String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
			mongoClient = new MongoClient(new MongoClientURI(dbURI));
			MongoDatabase db = mongoClient.getDatabase(db_name);
			
			//Creating ingredient list
			MongoCollection<Document> colIngr = db.getCollection("hebData");
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
			MongoCollection<Document> colNut = db.getCollection("recipes");
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
	}

	public static DBCollectionListObj getFromDB(String collection_name, int start, int total) throws Exception {
		
		getConnection();
		
		ArrayList<Document> list = new ArrayList<Document>();
		if (collection_name == "hebData") list = ingredientList;
		else if (collection_name == "recipes") list = recipeList;
		else if (collection_name == "nutrients") list = nutrientList;
		else throw new Exception("Invalid collection name");
		
		int last = list.size() / total;
		if (list.size() % total > 0) {
			last++;
		}
		
		int end = list.size();
		if (!(list.size() - start < total)) end = start + total;
		
		ArrayList<Document> subList = new ArrayList<Document>(list.subList(start, end));
		
		DBCollectionListObj output = new DBCollectionListObj(subList, start, end, last);

		return output;
	}

//	// Method to search a user in the mongodb
//	public static boolean searchFood(String food) {
//		boolean food_found = false;
//		String db_name = "wellbeing", db_collection_name = "hebData";
//
//		// Get the mongodb connection
//		MongoDatabase db = getConnection().getDatabase(db_name);
//
//		// Get the mongodb collection.
//		MongoCollection<Document> col = db.getCollection(db_collection_name);
//
//		// Get the particular record from the mongodb collection
//		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
//		obj.add(new BasicDBObject("item", food));
//
//		// Form a where query
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put("$and", obj);
//		System.out.println("Sql query is?= " + whereQuery.toString());
//
//		FindIterable<Document> cursor = col.find(whereQuery);
//		for (Document doc : cursor) {
//			System.out.println("Found?= " + doc);
//			food_found = true;
//		}
//		return food_found;
//	}
//
//	// Method to create a list of recipes
//	public static FindIterable<Document> getRecipes() {
//		String db_name = "wellbeing", db_collection_name = "recipes";
//
//		// Get the mongodb connection
//		MongoDatabase db = getConnection().getDatabase(db_name);
//
//		// Get the mongodb collection.
//		MongoCollection<Document> col = db.getCollection(db_collection_name);
//		FindIterable<Document> elements = col.find();
//		return elements;
//	}
//
//	public static FindIterable<Document> getIngredients() {
//		String db_name = "wellbeing", db_collection_name = "hebData";
//
//		// Get the mongodb connection
//		MongoDatabase db = getConnection().getDatabase(db_name);
//
//		// Get the mongodb collection.
//		MongoCollection<Document> col = db.getCollection(db_collection_name);
//		FindIterable<Document> elements = col.find();
//		return elements;
//	}
//
//	public static FindIterable<Document> getNutrients() {
//		String db_name = "wellbeing", db_collection_name = "nutrients";
//
//		// Get the mongodb connection
//		MongoDatabase db = getConnection().getDatabase(db_name);
//
//		// Get the mongodb collection.
//		MongoCollection<Document> col = db.getCollection(db_collection_name);
//		FindIterable<Document> elements = col.find();
//		return elements;
//	}
}
