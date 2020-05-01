package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IngredientInstanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String instanceTitle = request.getParameter("ingredientTitle");
        Ingredient ingredient = new Ingredient();
        DatabaseSingleton.getInstance();
		for(Ingredient d: DatabaseSingleton.getInstance().getIngredients()) {
            if(d.gettitle().equals(instanceTitle)){
                ingredient = d;
            }
        }
		System.out.println(instanceTitle);
        request.setAttribute("ingredient", ingredient);
        request.getRequestDispatcher("IngredientInstance.jsp").forward(request, response);
    }
}
