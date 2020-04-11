package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        List<Recipe> recipe = new ArrayList<Recipe>();
        for(Document d: DatabaseSingleton.getInstance().getRecipes()) {
            recipe.add(new Recipe(d));
        }
        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("/pages/recipes.jsp").forward(request, response);
    }
}
