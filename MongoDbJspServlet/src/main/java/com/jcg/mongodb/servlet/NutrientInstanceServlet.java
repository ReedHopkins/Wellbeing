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
        for(Document d: DatabaseSingleton.getInstance().getNutrients()) {
            if(((String) d.get("title")).equals(instanceTitle)){
                nutrient = new Nutrient(d);
            }
        }
        request.setAttribute("nutrient", nutrient);
        request.getRequestDispatcher("/pages/NutrientInstance.jsp").forward(request, response);
    }
}
