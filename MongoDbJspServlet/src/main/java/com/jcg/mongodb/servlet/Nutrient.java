package com.jcg.mongodb.servlet;

import java.util.Comparator;
import java.util.List;
import org.bson.Document;


public class Nutrient extends AbstractModel {
	String description;
	String dailyIntake;
	List<String> tags;
	String medicalInfo;

	public Nutrient(Document nutrientDoc) {
		title = (String) nutrientDoc.get("title");
		description = ((String) nutrientDoc.get("description")).trim();
		dailyIntake = ((String) nutrientDoc.get("reccommendedDailyIntake")).trim();
		image = (String) nutrientDoc.get("image");
		medicalInfo = (String) nutrientDoc.get("medicalInfo");
		tags = (List<String>) nutrientDoc.get("tags");

		dailyIntake = dailyIntake.replace("-", "&ndash;"); // normal keyboard dash to HTML dash
		dailyIntake = dailyIntake.replace("–", "&ndash;"); // special character dash to HTML dash
		description = description.substring(0, 1).toUpperCase() + description.substring(1);
	}

	public Nutrient() {
		title = "error";
		description = "error";
		dailyIntake = "error";
		image = "error";
	}

	public String getdescription() {
		return description;
	}

	public String getdailyIntake() {
		return dailyIntake;
	}

	public String getmedicalInfo() {return medicalInfo;}

	public boolean isMatch(String s) {

		if (title.toLowerCase().contains(s) || description.toLowerCase().contains(s)
				|| dailyIntake.toLowerCase().contains(s)) {
			return true;
		} else {
			for (String tag : tags) {
				System.out.println("testing nutrient tags");
				System.out.println(tag.toLowerCase());
				System.out.println(s);
				if (tag.toLowerCase().contains(s)) {
					return true;
				}
			}
		}
		return false;
	}
}

class SortNutrientsByIntake implements Comparator<Nutrient> {
	// Used for sorting in ascending order of name
	public int compare(Nutrient a, Nutrient b) {
		String compA = a.dailyIntake.split("&ndash;")[0];
		String compB = b.dailyIntake.split("&ndash;")[0];
		compA = compA.split(" ")[0];
		compB = compB.split(" ")[0];
		compA = compA.replaceAll("[^\\d.]", "");
		compB = compB.replaceAll("[^\\d.]", "");

		if (compA.isEmpty())
			return -1;
		if (compB.isEmpty())
			return 1;

		if (Double.parseDouble(compA) < Double.parseDouble(compB))
			return -1;
		if (Double.parseDouble(compA) > Double.parseDouble(compB))
			return 1;
		return 0;
	}
}