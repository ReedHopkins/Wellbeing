package com.jcg.mongodb.servlet;

import org.bson.Document;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NutrientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Nutrient> nutrient = new ArrayList<Nutrient>();
        for(Nutrient d: DatabaseSingleton.getInstance().getNutrients()) {
            nutrient.add(d);
        }
        request.setAttribute("nutrient", nutrient);
        request.getRequestDispatcher("/pages/nutrients.jsp").forward(request, response);
    }
}
