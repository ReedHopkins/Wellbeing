package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NutrientInstanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String instanceTitle = request.getParameter("nutrientTitle");
        Nutrient nutrient = new Nutrient();
        DatabaseSingleton.getInstance();
		for(Nutrient d: DatabaseSingleton.getNutrients()) {
            if(d.gettitle().equals(instanceTitle)){
                nutrient = d;
            }
        }
		Recipe[] top3 = DatabaseSingleton.topThreeRecipes(instanceTitle);
		System.out.println(instanceTitle);
		request.setAttribute("top3", top3);
        request.setAttribute("nutrient", nutrient);
        request.getRequestDispatcher("NutrientInstance.jsp").forward(request, response);
    }
}
