package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecipeInstanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String instanceTitle = request.getParameter("recipeTitle");
        Recipe recipe = new Recipe();
        for(Document d: DatabaseSingleton.getInstance().getRecipes()) {
            if(((String) d.get("title")).equals(instanceTitle)){
                recipe = new Recipe(d);
            }
        }
        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("/pages/RecipeInstance.jsp").forward(request, response);
    }
}
