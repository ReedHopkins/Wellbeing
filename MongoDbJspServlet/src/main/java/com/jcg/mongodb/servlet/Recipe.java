package com.jcg.mongodb.servlet;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Recipe {

	String id;
	String title;
	String readyInMinutes;
	String servings;
	String healthScore;
	String image;

	String carbs;
	String calories;
	String fat;
	String protein;
	List<Document> good;
	List<Document> bad;
	String instructions;
	List<Document> ingredients;
	List<String> tags = new ArrayList<String>();;

	public Recipe(Document recipe) {

		id = Integer.toString((Integer) recipe.get("id"));

		title = (String) recipe.get("title");
		readyInMinutes = Integer.toString((Integer) recipe.get("readyInMinutes"));
		servings = Integer.toString((Integer) recipe.get("servings"));
		healthScore = Double.toString((Double) recipe.get("healthScore"));
		image = (String) recipe.get("image");

		carbs = (String) recipe.get("carbs");
		fat = (String) recipe.get("fat");
		calories = (String) recipe.get("calories");
		protein = (String) recipe.get("protein");
		good = (List<Document>) recipe.get("good");
		bad = (List<Document>) recipe.get("bad");
		instructions = (String) recipe.get("instructions");
		ingredients = (List<Document>) recipe.get("extendedIngredients");

		if (Double.parseDouble(healthScore) >= 50.0) {
			tags.add("healthy");
		} else if (Double.parseDouble(healthScore) < 50.0) {
			tags.add("unhealthy");
		}

		if (protein != null) {
			if (Double.parseDouble(protein.replaceAll("[^\\d.]", "")) > 10.0)
				tags.add("highprotein");
		}
		if (carbs != null) {
			if (Double.parseDouble(carbs.replaceAll("[^\\d.]", "")) < 15.0)
				tags.add("lowcarb");
		}

	}

	public Recipe() {
		id = "error";
		title = "error";
		readyInMinutes = "error";
		servings = "error";
		healthScore = "error";
		image = "error";

		carbs = "error";
		calories = "error";
		fat = "error";
		protein = "error";
		good = new ArrayList<Document>();
		bad = new ArrayList<Document>();
		instructions = "error";
		ingredients = new ArrayList<Document>();
	}

	public String getid() {
		return id;
	}

	public String gettitle() {
		return title;
	}

	public String getreadyInMinutes() {
		return readyInMinutes;
	}

	public String getservings() {
		return servings;
	}

	public String gethealthScore() {
		return healthScore;
	}

	public String getimage() {
		return image;
	}

	public String getcarbs() {
		return carbs;
	}

	public String getcalories() {
		return calories;
	}

	public String getfat() {
		return fat;
	}

	public String getprotein() {
		return protein;
	}

	public List<Document> getgoodnutrients() {
		return good;
	}

	public List<Document> getbadnutrients() {
		return bad;
	}

	public String getinstructions() {
		return instructions;
	}

	public List<Document> getingredients() {
		return ingredients;
	}

	public boolean isMatch(String s) {

		if (title.toLowerCase().contains(s) || readyInMinutes.toLowerCase().contains(s)
				|| servings.toLowerCase().contains(s) || healthScore.toLowerCase().contains(s)) {
			return true;
		} else {
			for (String tag : tags) {
				if (tag.toLowerCase().equals(s)) {
					return true;
				}
			}
		}

			return false;
		}
	}

	class SortRecipesByName implements Comparator<Recipe> {
		// Used for sorting in ascending order of name
		public int compare(Recipe a, Recipe b) {
			return a.title.toLowerCase().compareTo(b.title.toLowerCase());
		}
	}

	class SortRecipesByTime implements Comparator<Recipe> {
		// Used for sorting in ascending order of name
		public int compare(Recipe a, Recipe b) {
			return Integer.parseInt(a.readyInMinutes) - Integer.parseInt(b.readyInMinutes);
		}
	}

	class SortRecipesByServings implements Comparator<Recipe> {
		// Used for sorting in ascending order of name
		public int compare(Recipe a, Recipe b) {
			return Integer.parseInt(a.servings) - Integer.parseInt(b.servings);
		}
	}

	class SortRecipesByIngredients implements Comparator<Recipe> {
		// Used for sorting in ascending order of name
		public int compare(Recipe a, Recipe b) {
			return a.ingredients.size() - b.ingredients.size();
		}
	}

class SortRecipesByHealth implements Comparator<Recipe> { 
    // Used for sorting in ascending order of name
    public int compare(Recipe a, Recipe b) 
    { 
    	if (Double.parseDouble(a.healthScore) < Double.parseDouble(b.healthScore))
			return -1;
		if (Double.parseDouble(a.healthScore) > Double.parseDouble(b.healthScore))
			return 1;
		return 0;
    } 
}