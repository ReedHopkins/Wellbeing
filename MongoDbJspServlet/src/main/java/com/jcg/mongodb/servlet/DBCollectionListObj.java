package com.jcg.mongodb.servlet;

import java.util.ArrayList;

import org.bson.Document;

public class DBCollectionListObj {
	
	private ArrayList<Document> elements;
	private int startIndex;
	private int endIndex;
	private int last;
	
	public DBCollectionListObj(ArrayList<Document> list, int s, int e, int l) {
		elements = list;
		startIndex = s;
		endIndex = e;
		last = l;
	}
	
	public ArrayList<Document> getSubList() {
		return elements;
	}
	
	public int getLast() {
		return last;
	}

}
