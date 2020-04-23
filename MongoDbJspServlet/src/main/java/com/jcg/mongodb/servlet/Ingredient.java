package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;

public class Ingredient {
	String item;
	String price;
	String unit;
	String image;
	List<String> nutrients;
	List<String> tags;
	List<String> badges;
	String aisle;
	public Ingredient(Document ingredient) {
		item = ((String) ingredient.get("item"));
		item = capitalize(item);
		price = ((String) ingredient.get("price")).replace(",", "");
		unit = (String) ingredient.get("unit");
		nutrients = (List<String>) ingredient.get("nutrients");
		image = (String) ingredient.get("image");
		tags = (List<String>) ingredient.get("tags");
        badges = (List<String>) ingredient.get("badges");
        aisle = (String) ingredient.get("aisle");
	}

	public Ingredient() {
		item = "error";
		price = "error";
		unit = "error";
		image = "error";
		nutrients = new ArrayList<String>();
		tags = new ArrayList<String>();
        badges = new ArrayList<String>();
        aisle = "error";
	}

	private String capitalize(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other
																									// chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}

	public String getitem() {
		return item;
	}

	public String getprice() {
		return price;
	}

	public String getunit() {
		return unit;
	}

	public String getimage() {
		return image;
	}

	public List<String> getnutrients() {
		return nutrients;
	}

	public List<String> gettags() {
		return tags;
	}
    
    public List<String> getbadges() {
        return badges;
    }
    
    public String getaisle() {
        return aisle;
    }

	public boolean isMatch(String s) {

		if (item.toLowerCase().contains(s) || price.toLowerCase().contains(s) || unit.toLowerCase().contains(s)) {
			return true;
		} else {
			for (String tag : tags) {
				if (tag.toLowerCase().replace(" ", "").equals(s)) {
					return true;
				}
			}
		}

			return false;
		}
	}

	class SortIngredientsByName implements Comparator<Ingredient> {
		// Used for sorting in ascending order of name
		public int compare(Ingredient a, Ingredient b) {
			return a.item.toLowerCase().compareTo(b.item.toLowerCase());
		}
	}

class SortIngredientsByPrice implements Comparator<Ingredient> {
	// Used for sorting in ascending order of name
	public int compare(Ingredient a, Ingredient b) {
		if (Double.parseDouble(a.price) < Double.parseDouble(b.price))
			return -1;
		if (Double.parseDouble(a.price) > Double.parseDouble(b.price))
			return 1;
		return 0;
	}
}
