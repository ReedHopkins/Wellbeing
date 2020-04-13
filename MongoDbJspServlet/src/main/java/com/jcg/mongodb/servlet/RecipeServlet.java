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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Retrieve all ingredients in database
		DatabaseSingleton.getInstance();
		ArrayList<Recipe> recipes = DatabaseSingleton.getRecipes();

		//begin pagination calculations
		String spageid = request.getParameter("page");
		String search_term = request.getParameter("search_term");
		
		if (search_term != null) {
			System.out.println(search_term);
			doPost(request, response);
			return;
		}

		if (spageid == null) spageid = "1";
		
		int pageId = Integer.parseInt(spageid);
		int total = 10;

		int start = Util.getStartIndex(pageId, total);
		int last = Util.getLastPage(recipes.size(), total);
		int end = Util.getEndIndex(start, recipes.size(), total);
		ArrayList<Integer> pageNums = Util.getPaginatorNums(pageId, last);
		
		System.out.println(pageNums);
		
		ArrayList<Recipe> subList = new ArrayList<Recipe>(recipes.subList(start, end));
		
		String previous = "#";
		if (pageId > 1) previous = "RecipeServlet?page=" + Integer.toString(pageId - 1);	
		String next = "#";
		if (pageId < last) next = "RecipeServlet?page=" + Integer.toString(pageId + 1);

		//set calculated attributes
		request.setAttribute("subtitle", "All Recipes:");
		request.setAttribute("recipe", subList);
		request.setAttribute("pageNums", pageNums);
		request.setAttribute("previous", previous);
		request.setAttribute("next", next);
		request.setAttribute("first", "RecipeServlet?page=1");
		request.setAttribute("last", "RecipeServlet?page=" + last);
		request.getRequestDispatcher("/pages/recipes.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String subtitle = "Search Results:";

		// Reading post parameters from the request
		String search_param = request.getParameter("search_term");
		
		search_param.toLowerCase();

		//Search for matched ingredients
		DatabaseSingleton.getInstance();
		ArrayList<Recipe> recipes = DatabaseSingleton.searchRecipes(search_param);

		//begin pagination calculations
		String spageid = request.getParameter("page");

		if (spageid == null) spageid = "1";
		
		int pageId = Integer.parseInt(spageid);
		int total = 10;

		int start = Util.getStartIndex(pageId, total);
		int last = Util.getLastPage(recipes.size(), total);
		int end = Util.getEndIndex(start, recipes.size(), total);
		ArrayList<Integer> pageNums = Util.getPaginatorNums(pageId, last);
				
		ArrayList<Recipe> subList = new ArrayList<Recipe>(recipes.subList(start, end));
		
		String previous = "#";
		if (pageId > 1) previous = "RecipeServlet?search_term=" + search_param + "&page=" + Integer.toString(pageId - 1);	
		String next = "#";
		if (pageId < last) next = "RecipeServlet?search_term=" + search_param + "&page=" + Integer.toString(pageId + 1);
		
		String showPagination = "default";
		if (subList.size() < 1) {
			showPagination = "none";
			subtitle = "No results found...";
		}
		
		
		//set calculated attributes
		request.setAttribute("subtitle", subtitle);
		request.setAttribute("recipe", subList);
		request.setAttribute("pageNums", pageNums);
		request.setAttribute("previous", previous);
		request.setAttribute("next", next);
		request.setAttribute("first", "RecipeServlet?search_term=" + search_param + "&page=1");
		request.setAttribute("last", "RecipeServlet?search_term=" + search_param + "&page=" + last);
		request.setAttribute("search_term", search_param);
		request.setAttribute("showPagination", showPagination);
		request.getRequestDispatcher("/pages/recipes.jsp").forward(request, response);
	}

}
