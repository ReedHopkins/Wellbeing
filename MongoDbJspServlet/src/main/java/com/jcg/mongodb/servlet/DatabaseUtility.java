package com.jcg.mongodb.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DatabaseUtility {
    private static HashMap<Integer, Recipe> recipeMap;
    private static HashMap<Integer, Ingredient> ingredientMap, secingredientMap;
    private static HashMap<Integer, Nutrient> nutrientMap, secnutrientMap;

    public static void initializeMaps(ArrayList<Ingredient> ingredients, ArrayList<Recipe> recipes, ArrayList<Nutrient> nutrients){
        recipeMap = new HashMap<Integer, Recipe>();
        for(Recipe r: recipes){
            recipeMap.put( r.title.toLowerCase().hashCode(), r);
        }
        ingredientMap = new HashMap<Integer, Ingredient>();
        secingredientMap = new HashMap<Integer, Ingredient>();
        for(Ingredient ing: ingredients){
            ingredientMap.put( ing.title.toLowerCase().hashCode(), ing);
            putMap(ing.gettitle(), ing, secingredientMap);
        }
        nutrientMap = new HashMap<Integer, Nutrient>();
        secnutrientMap = new HashMap<Integer, Nutrient>();
        for(Nutrient n: nutrients){
            nutrientMap.put( n.title.toLowerCase().hashCode(), n);
            String temp = nutrientStringFilter(n.title);
            putMap(temp, n, secnutrientMap);
        }
        secnutrientMap.remove("beta".hashCode());
        secnutrientMap.remove("acid".hashCode());
        secnutrientMap.remove("acids".hashCode());
    }

    public static ArrayList<Ingredient> searchIngredients(String s){
        ArrayList<Ingredient> output = new ArrayList<Ingredient>();
        String[] s_split = s.split(" ");
        for (Ingredient i: DatabaseSingleton.getInstance().getIngredients()) {
            for (String elem: s_split) {
                if (i.isMatch(elem)) {
                    output.add(i);
                }
            }
        }
        return output;
    }

    public static ArrayList<Recipe> searchRecipes(String s){
        ArrayList<Recipe> output = new ArrayList<Recipe>();
        String[] s_split = s.split(" ");
        for (Recipe i: DatabaseSingleton.getInstance().getRecipes()) {
            for (String elem: s_split) {
                if (i.isMatch(elem)) {
                    output.add(i);
                }
            }

        }
        return output;
    }

    public static ArrayList<Nutrient> searchNutrients(String s){
        ArrayList<Nutrient> output = new ArrayList<Nutrient>();
        String[] s_split = s.split(" ");
        for (Nutrient i: DatabaseSingleton.getInstance().getNutrients()) {
            for (String elem: s_split) {
                if (i.isMatch(elem)) {
                    output.add(i);
                }
            }

        }
        return output;
    }

    public static ArrayList<Ingredient> getSortedIngredients(String sort) {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(DatabaseSingleton.getInstance().getIngredients());

        if (sort.equals("atoz") || sort.equals("ztoa")) {
            Collections.sort(ingredients, new SortIngredientsByName());
            if (sort.contentEquals("ztoa")) {
                Collections.reverse(ingredients);
            }
        } else if (sort.equals("lowtohigh") || sort.contentEquals("hightolow")) {
            Collections.sort(ingredients, new SortIngredientsByPrice());
            if (sort.contentEquals("hightolow")) {
                Collections.reverse(ingredients);
            }
        }
        return ingredients;
    }

    public static ArrayList<Recipe> getSortedRecipes(String sort) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>(DatabaseSingleton.getInstance().getRecipes());

        if (sort.equals("atoz") || sort.equals("ztoa")) {
            Collections.sort(recipes, new SortRecipesByName());
            if (sort.contentEquals("ztoa")) {
                Collections.reverse(recipes);
            }
        } else if (sort.equals("timelowtohigh") || sort.contentEquals("timehightolow")) {
            Collections.sort(recipes, new SortRecipesByTime());
            if (sort.contentEquals("timehightolow")) {
                Collections.reverse(recipes);
            }
        } else if (sort.equals("servingslowtohigh") || sort.contentEquals("servingshightolow")) {
            Collections.sort(recipes, new SortRecipesByServings());
            if (sort.contentEquals("servingshightolow")) {
                Collections.reverse(recipes);
            }
        } else if (sort.equals("ingredientslowtohigh") || sort.contentEquals("ingredientshightolow")) {
            Collections.sort(recipes, new SortRecipesByIngredients());
            if (sort.contentEquals("ingredientshightolow")) {
                Collections.reverse(recipes);
            }
        } else if (sort.equals("healthlowtohigh") || sort.contentEquals("healthhightolow")) {
            Collections.sort(recipes, new SortRecipesByHealth());
            if (sort.contentEquals("healthhightolow")) {
                Collections.reverse(recipes);
            }
        }
        return recipes;
    }

    public static ArrayList<Nutrient> getSortedNutrients(String sort) {
        ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>(DatabaseSingleton.getInstance().getNutrients());

        if (sort.equals("atoz") || sort.equals("ztoa")) {
            Collections.sort(nutrients, new SortNutrientsByName());
            if (sort.contentEquals("ztoa")) {
                Collections.reverse(nutrients);
            }
        } else if (sort.equals("intakelowtohigh") || sort.contentEquals("intakehightolow")) {
            Collections.sort(nutrients, new SortNutrientsByIntake());
            if (sort.contentEquals("intakehightolow")) {
                Collections.reverse(nutrients);
            }
        }
        return nutrients;
    }

    public static ArrayList<Recipe> searchRecipesForIngredient(String ingredient){
        ArrayList<Recipe> output = new ArrayList<Recipe>();
        for(Recipe r: DatabaseSingleton.getInstance().getRecipes()){
            if(r.ingredientMap.containsKey(ingredient.toLowerCase().hashCode())){
                output.add(r);
            }
        }
        return output;
    }

    public static String findIngredient(String s){
        Ingredient ingredient = (Ingredient) findMap(s, ingredientMap);
        if(ingredient != null){
            return ingredient.title;
        }
        ingredient = (Ingredient) findMap(s, secingredientMap);
        if(ingredient != null){
            return ingredient.title;
        }
        return null;
    }

    public static String findNutrient(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.substring(i,i+1).equals(",") || s.substring(i,i+1).equals(":") || s.substring(i,i+1).equals("-") || s.substring(i,i+1).equals("(") || s.substring(i,i+1).equals(")")){
                s = s.substring(0,i) + s.substring(i+1);
                i--;
            }
        }
        Nutrient nutrient = (Nutrient) findMap(s, nutrientMap);
        if(nutrient != null){
            return nutrient.title;
        }
        nutrient = (Nutrient) findMap(s, secnutrientMap);
        if(nutrient != null){
            return nutrient.title;
        }
        return null;
    }

    public static ArrayList<Recipe> topThreeRecipes(String nutrient){
        String[] tokens = tokenize(nutrient);
        int tokenSize = tokens.length;
        ArrayList<Recipe> matches = new ArrayList<Recipe>();
        String findString;
        for(Recipe r: DatabaseSingleton.getInstance().getRecipes()){
            for(int i = tokenSize; i > 0; i--){
                for(int j = 0; j <= tokenSize - i; j++){
                    findString = tokens[j];
                    for(int k = j+1; k < i+j; k++){
                        findString += " " + tokens[k];
                    }
                    if(r.nutrientMap.containsKey(findString.hashCode())){
                        matches.add(r);
                    }
                }
            }
        }
        Collections.shuffle(matches);
        return matches;
    }

    public static Ingredient[] topThreeIngredients(String nutrient){
        String temp = nutrientStringFilter(nutrient);
        String[] tokens = tokenize(temp.toLowerCase());
        int tokenSize = tokens.length;
        double[] top3 = {0.0,0.0,0.0};
        Ingredient[] top3Objects = new Ingredient[3];
        String findString;
        for(Ingredient ing: DatabaseSingleton.getInstance().getIngredients()){
            for(int i = tokenSize; i > 0; i--){
                for(int j = 0; j <= tokenSize - i; j++){
                    findString = tokens[j];
                    for(int k = j+1; k < i+j; k++){
                        findString += " " + tokens[k];
                    }
                    if(ing.nutrientMap.containsKey(findString.hashCode())){
                        Double recipeValue = ing.nutrientMap.get(findString.hashCode());
                        if(recipeValue>top3[0] && top3Objects[0] != ing){
                            top3[2] = top3[1];
                            top3[1] = top3[0];
                            top3Objects[2] = top3Objects[1];
                            top3Objects[1] = top3Objects[0];
                            top3[0] = recipeValue;
                            top3Objects[0] = ing;
                        } else if(recipeValue>top3[1] && top3Objects[1] != ing){
                            top3[2] = top3[1];
                            top3Objects[2] = top3Objects[1];
                            top3[1] = recipeValue;
                            top3Objects[1] = ing;
                        } else if(recipeValue>top3[2] && top3Objects[2] != ing){
                            top3[2] = recipeValue;
                            top3Objects[2] = ing;
                        }
                    }
                }
            }
        }
        if(top3Objects[0] == null && top3Objects[1] == null && top3Objects[2] == null){
            return null;
        }
        return top3Objects;
    }

    public static String[] tokenize(String s){
        StringTokenizer strtok = new StringTokenizer(s.toLowerCase());
        int tokenSize = strtok.countTokens();
        String[] tokens = new String[tokenSize];
        int iter = 0;
        while(strtok.hasMoreTokens()){
            tokens[iter] = strtok.nextToken();
            iter++;
        }
        return tokens;
    }

    public static String nutrientStringFilter(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.substring(i,i+1).equals("(") || s.substring(i,i+1).equals(")")){
                s = s.substring(0,i) + s.substring(i+1);
                i--;
            }
            if(s.substring(i,i+1).equals("-")){
                s = s.substring(0,i) + " " + s.substring(i+1);
            }
        }
        return s;
    }

    public static void putMap(String s, Object o, HashMap m){
        String[] tokens = tokenize(s);
        int tokenSize = tokens.length;
        String findString;
        for(int i = tokenSize; i > 0; i--){
            for(int j = 0; j <= tokenSize - i; j++){
                findString = tokens[j];
                for(int k = j+1; k < i+j; k++){
                    findString += " " + tokens[k];
                }
                m.put(findString.hashCode(), o);
            }
        }
    }

    public static Object findMap(String s, HashMap m){
        String[] tokens = tokenize(s);
        int tokenSize = tokens.length;
        String findString;
        for(int i = tokenSize; i > 0; i--){
            for(int j = 0; j <= tokenSize - i; j++){
                findString = tokens[j];
                for(int k = j+1; k < i+j; k++){
                    findString += " " + tokens[k];
                }
                if(m.containsKey(findString.hashCode())){
                    return m.get(findString.hashCode());
                }
            }
        }
        return null;
    }
}
