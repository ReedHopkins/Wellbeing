package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeInstanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("recipeId");
        Recipe recipe = new Recipe();
        DatabaseSingleton.getInstance();
		for(Recipe d: DatabaseSingleton.getInstance().getRecipes()) {
            if(d.getid().equals(id)){
                recipe = d;
            }
        }

		// searching for matching nutrients to construct links
        List<String> nutrientURL = new ArrayList<String>();
        for (Document nutrient : recipe.getgoodnutrients()){

            for(Nutrient match: DatabaseSingleton.getInstance().getNutrients()) {
                if(nutrient.getString("title").equals(match.gettitle())){
                    nutrientURL.add("NutrientInstanceServlet?nutrientTitle=" + match.gettitle());
                }
            }
        }
        for (Document nutrient : recipe.getbadnutrients()){

            for(Nutrient match: DatabaseSingleton.getInstance().getNutrients()) {
                if(nutrient.getString("title").equals(match.gettitle())){
                    nutrientURL.add("NutrientInstanceServlet?nutrientTitle=" + match.gettitle());
                }
            }
        }

        List<String> ingredientURL = new ArrayList<String>();
        request.setAttribute("recipe", recipe);
        request.setAttribute("nutrientURL", nutrientURL);
        request.setAttribute("ingredientURL", ingredientURL);
        request.getRequestDispatcher("RecipeInstance.jsp").forward(request, response);
    }
}
