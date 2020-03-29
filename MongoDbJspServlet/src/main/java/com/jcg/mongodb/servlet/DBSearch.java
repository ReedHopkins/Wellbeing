package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBSearch {

	// Method to make a connection to the mongodb server listening on a default port
	private static MongoClient getConnection() {
		String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
		MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI));
		
		return mongoClient;
	}

	// Method to search a user in the mongodb
	public static boolean searchFood(String food) {
		boolean food_found = false;
		String db_name = "wellbeing", db_collection_name = "hebData";

		// Get the mongodb connection
		MongoDatabase db = getConnection().getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);

		// Get the particular record from the mongodb collection
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("item", food));

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);
		System.out.println("Sql query is?= " + whereQuery.toString());

		FindIterable<Document> cursor = col.find(whereQuery);
		for (Document doc : cursor) {
			System.out.println("Found?= " + doc);
			food_found = true;
		}
		return food_found;
	}
}
