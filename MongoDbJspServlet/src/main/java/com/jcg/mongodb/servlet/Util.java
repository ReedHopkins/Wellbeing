package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static int getStartIndex(int start, int total) {
		int output = 0;
		if (start == 1) {
			output = 0;
		} else {
			output = start - 1;
			output = output * total;
		}
		return output;
	}
	
	public static int getLastPage(int size, int total) {
		int last = size / total;
		if (size % total > 0) {
			last++;
		}
		return last;
	}
	
	public static int getEndIndex(int start, int size, int total) {
		int end = size;
		if (!(size - start < total))
			end = start + total;
		
		return end;
	}
	
	public static ArrayList<Integer> getPaginatorNums(int origPageId, int last) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		if (last == 4) {
			output.addAll(Arrays.asList(1, 2, 3, 4));
		} else if (last == 3){
			output.addAll(Arrays.asList(1, 2, 3));
		} else if (last == 2) {
			output.addAll(Arrays.asList(1, 2));
		} else if (last == 1) {
			output.addAll(Arrays.asList(1));
		} else if (last > 4) {
			
			int pageNum = 1;
			if (origPageId > 3) {
				pageNum = origPageId - 2;
			}
			if (origPageId > last - 2) {
				pageNum = origPageId - 3;
			}
			if (origPageId > last - 1) {
				pageNum = origPageId - 4;
			}
			for (int i = 0; i < 5; i++, pageNum++) {
				output.add(pageNum);
			}
			
		}
		
		return output;
	}

}