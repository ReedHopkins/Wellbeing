package com.jcg.mongodb.servlet;

import org.bson.Document;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ingredient> ingredient = new ArrayList<Ingredient>();
        for(Document d: DatabaseSingleton.getInstance().getIngredients()) {
            ingredient.add(new Ingredient(d));
        }
        request.setAttribute("ingredient", ingredient);
        request.getRequestDispatcher("/pages/ingredients.jsp").forward(request, response);
    }
}
