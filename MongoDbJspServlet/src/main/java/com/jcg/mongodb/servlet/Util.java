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

	public static DBCollectionListObj getFromDB(String collection_name, int start, int total) throws Exception {

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
}