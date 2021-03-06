package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.scene.chart.PieChart;
import org.bson.Document;

import static com.jcg.mongodb.servlet.DatabaseUtility.putMap;

public class Ingredient extends AbstractModel{
	String price;
	String unit;
	List<String> nutrients;
	List<String> tags;
	List<String> badges;
	String aisle;
	HashMap<Integer, Double> nutrientMap = new HashMap<Integer, Double>();
	public Ingredient(Document ingredient) {
		title = ((String) ingredient.get("title"));
		title = capitalize(title);
		price = ((String) ingredient.get("price")).replace(",", "");
		unit = (String) ingredient.get("unit");
		nutrients = (List<String>) ingredient.get("nutrients");
		image = (String) ingredient.get("image");
		tags = (List<String>) ingredient.get("tags");
        badges = (List<String>) ingredient.get("badges");
        aisle = (String) ingredient.get("aisle");
        for(String s: nutrients){
			for(int i = 0; i < s.length(); i++) {
				if (s.substring(i, i + 1).equals(",") || s.substring(i, i + 1).equals(":") || s.substring(i, i + 1).equals("-") || s.substring(i, i + 1).equals("(") || s.substring(i, i + 1).equals(")")) {
					s = s.substring(0, i) + s.substring(i + 1);
					i--;
				}
			}
			String[] tokens = DatabaseUtility.tokenize(s);
			int tokenSize = tokens.length;
			double val = Double.parseDouble(tokens[tokenSize-2]);
			putMap(s, val, nutrientMap);
		}
		nutrientMap.remove("beta".hashCode());
		nutrientMap.remove("acid".hashCode());
		nutrientMap.remove("acids".hashCode());
		nutrientMap.remove("vitamin".hashCode());
	}

	public Ingredient() {
		title = "error";
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

	public String getprice() {
		return price;
	}

	public String getunit() {
		return unit;
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
		if (title.toLowerCase().contains(s) || price.toLowerCase().contains(s) || unit.toLowerCase().contains(s)) {
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
