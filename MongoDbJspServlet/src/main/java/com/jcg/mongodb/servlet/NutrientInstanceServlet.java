package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class NutrientInstanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String instanceTitle = request.getParameter("nutrientTitle");
        Nutrient nutrient = new Nutrient();
        DatabaseSingleton.getInstance();
		for(Nutrient d: DatabaseSingleton.getInstance().getNutrients()) {
            if(d.gettitle().equals(instanceTitle)){
                nutrient = d;
            }
        }
		ArrayList<Recipe> recipes = DatabaseUtility.topThreeRecipes(instanceTitle);
		Ingredient[] top3ing = DatabaseUtility.topThreeIngredients(instanceTitle);
		System.out.println(instanceTitle);
		request.setAttribute("recipes", recipes);
        request.setAttribute("top3ing", top3ing);
        request.setAttribute("nutrient", nutrient);
        request.getRequestDispatcher("NutrientInstance.jsp").forward(request, response);
    }
}
