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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Retrieve all ingredients in database
		DatabaseSingleton.getInstance();
		ArrayList<Ingredient> ingredients = DatabaseSingleton.getIngredients();

		//begin pagination calculations
		String spageid = request.getParameter("page");
		String search_term = request.getParameter("search_term");
		
		if (search_term == null || "".equals(search_term)) {
			
			if (spageid == null) spageid = "1";
			
			int pageId = Integer.parseInt(spageid);
			int total = 10;

			int start = Util.getStartIndex(pageId, total);
			int last = Util.getLastPage(ingredients.size(), total);
			int end = Util.getEndIndex(start, ingredients.size(), total);
			ArrayList<Integer> pageNums = Util.getPaginatorNums(pageId, last);
						
			ArrayList<Ingredient> subList = new ArrayList<Ingredient>(ingredients.subList(start, end));
			
			String previous = "#";
			if (pageId > 1) previous = "IngredientServlet?page=" + Integer.toString(pageId - 1);	
			String next = "#";
			if (pageId < last) next = "IngredientServlet?page=" + Integer.toString(pageId + 1);

			//set calculated attributes
			request.setAttribute("subtitle", "All Ingredients:");
			request.setAttribute("ingredient", subList);
			request.setAttribute("pageNums", pageNums);
			request.setAttribute("previous", previous);
			request.setAttribute("next", next);
			request.setAttribute("first", "IngredientServlet?page=1");
			request.setAttribute("last", "IngredientServlet?page=" + last);
			request.getRequestDispatcher("/pages/ingredients.jsp").forward(request, response);
			
		} else {
			System.out.println(search_term);
			doPost(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String subtitle = "Search Results:";

		// Reading post parameters from the request
		String search_param = request.getParameter("search_term");
		
		
		search_param = search_param.toLowerCase();

		//Search for matched ingredients
		DatabaseSingleton.getInstance();
		ArrayList<Ingredient> ingredients = DatabaseSingleton.searchIngredients(search_param);

		//begin pagination calculations
		String spageid = request.getParameter("page");

		if (spageid == null) spageid = "1";
		
		int pageId = Integer.parseInt(spageid);
		int total = 10;

		int start = Util.getStartIndex(pageId, total);
		int last = Util.getLastPage(ingredients.size(), total);
		int end = Util.getEndIndex(start, ingredients.size(), total);
		ArrayList<Integer> pageNums = Util.getPaginatorNums(pageId, last);
				
		ArrayList<Ingredient> subList = new ArrayList<Ingredient>(ingredients.subList(start, end));
		
		String previous = "#";
		if (pageId > 1) previous = "IngredientServlet?search_term=" + search_param + "&page=" + Integer.toString(pageId - 1);	
		String next = "#";
		if (pageId < last) next = "IngredientServlet?search_term=" + search_param + "&page=" + Integer.toString(pageId + 1);
		
		String showPagination = "default";
		if (subList.size() < 1) {
			showPagination = "none";
			subtitle = "No results found...";
		}
		
		
		//set calculated attributes
		request.setAttribute("subtitle", subtitle);
		request.setAttribute("ingredient", subList);
		request.setAttribute("pageNums", pageNums);
		request.setAttribute("previous", previous);
		request.setAttribute("next", next);
		request.setAttribute("first", "IngredientServlet?search_term=" + search_param + "&page=1");
		request.setAttribute("last", "IngredientServlet?search_term=" + search_param + "&page=" + last);
		request.setAttribute("search_term", search_param);
		request.setAttribute("showPagination", showPagination);
		request.getRequestDispatcher("/pages/ingredients.jsp").forward(request, response);
	}

}
