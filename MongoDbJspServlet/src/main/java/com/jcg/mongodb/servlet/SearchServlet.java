package com.jcg.mongodb.servlet;

import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchServlet extends HttpServlet{
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	    	
    	// Reading post parameters from the request
        String search_param = request.getParameter("ingredient_id").toLowerCase();
        
        if (search_param == null || search_param.equals("")) {
        	request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
        }
        
        ArrayList<Ingredient> searchResults = DatabaseSingleton.getInstance().searchIngredients(search_param);
        
        request.setAttribute("results", searchResults);
        request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
 
//        // Checking for null and empty values
//        if(search_param == null || "".equals(search_param)) {
////            req.setAttribute("error_message", "Please enter login id and password");
////            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        } else {
//        	ArrayList<Document> searchResults = DatabaseSingleton.getIngredients();
//            if(searchResults.size() > 0) {               
//                req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
//            } else {
//                req.setAttribute("error_message", "You are not an authorised user. Please check with administrator.");
//                req.getRequestDispatcher("/index.jsp").forward(req, resp);
//            }   
//        }  
//        List<Recipe> recipe = new ArrayList<Recipe>();
//        for(Document d: DatabaseSingleton.getInstance().getRecipes()) {
//            recipe.add(new Recipe(d));
//        }
//        request.setAttribute("recipe", recipe);
//        request.getRequestDispatcher("/pages/recipes.jsp").forward(request, response);
    }
}
