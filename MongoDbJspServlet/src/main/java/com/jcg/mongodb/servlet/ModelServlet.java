package com.jcg.mongodb.servlet;

import org.bson.Document;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ModelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String model = request.getParameter("model");
		String filter = request.getParameter("filter");
		String search_term = request.getParameter("search_term");
		String sort = request.getParameter("sort");
		
		boolean nosearch = search_term == null || "".equals(search_term);
		boolean nofilter = filter == null || "".equals(filter);
		boolean nosort = sort == null || "".equals(sort);

		// Retrieve all elements in database based on model
		DatabaseSingleton.getInstance();
		ArrayList<?> list = new ArrayList<Object>();
		
		if (!nosort) {
			if (model.equals("Ingredient")) list = DatabaseSingleton.getSortedIngredients(sort);
			if (model.equals("Recipe")) list = DatabaseSingleton.getRecipes();
			if (model.equals("Nutrient")) list = DatabaseSingleton.getNutrients();
		} else {
			if (model.equals("Ingredient")) list = DatabaseSingleton.getIngredients();
			if (model.equals("Recipe")) list = DatabaseSingleton.getRecipes();
			if (model.equals("Nutrient")) list = DatabaseSingleton.getNutrients();
		}
		
		int size = list.size();

		// begin pagination calculations
		String spageid = request.getParameter("page");
		if (spageid == null) {
			spageid = "1";
		}
		String subtitle = "All " + model + "s (Page " + spageid + "):";
		
		if (nosearch || nofilter) {
			
			Paginator Paginator = new Paginator(model, spageid, size, 9);
			if (!nosort) Paginator.setSortTerm(sort);
			ArrayList<?> subList = new ArrayList<Object>(list.subList(Paginator.getStartIndex(), Paginator.getEndIndex()));
			String params = "&search_term=" + search_term + "&sort=" + sort;

			// set calculated attributes
			request.setAttribute("subtitle", subtitle);
			request.setAttribute(model.toLowerCase(), subList);
			request.setAttribute("pageNums", Paginator.getPageNums());
			request.setAttribute("previous", Paginator.getPreviousPageLink());
			request.setAttribute("next", Paginator.getNextPageLink());
			request.setAttribute("first", Paginator.getFirstPageLink());
			request.setAttribute("last", Paginator.getLastPageLink());
			request.setAttribute("params", params);
			request.getRequestDispatcher(model.toLowerCase() + "s.jsp").forward(request, response);

		} else {
			doPost(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String model = request.getParameter("model");
		String search_term = request.getParameter("search_term");
		search_term = search_term.toLowerCase();
		String filter = request.getParameter("filter");
		String sort = request.getParameter("sort");
		
		String spageid = request.getParameter("page");
		if (spageid == null) {
			spageid = "1";
		}
		
		boolean nosearch = search_term == null || "".equals(search_term);
		boolean nofilter = filter == null || "".equals(filter);
		boolean nosort = sort == null || "".equals(sort);
		
		String subtitle = "Search Results (Page " + spageid + "):";

		// Search for matched nutrients
		DatabaseSingleton.getInstance();
		ArrayList<?> list = new ArrayList<Object>();
		ArrayList<?> subList = new ArrayList<Object>();
		Paginator Paginator = new Paginator();
		
		if (nofilter && nosort) { //do search
			
			if (model.equals("Ingredient")) list = DatabaseSingleton.searchIngredients(search_term);
			if (model.equals("Recipe")) list = DatabaseSingleton.searchRecipes(search_term);
			if (model.equals("Nutrient")) list = DatabaseSingleton.searchNutrients(search_term);
			
			int size = list.size();

			Paginator P = new Paginator(model, spageid, size, 9);
			P.setSearchTerm(search_term);
			
			subList = new ArrayList<Object>(list.subList(P.getStartIndex(), P.getEndIndex()));
			Paginator = P;
			
		} else if (nosearch && nosort) { //do filter
			
		} else if (nosearch && nofilter) { // do sort
			
		}

		String showPagination = "default";
		if (subList.size() < 1) {
			showPagination = "none";
			subtitle = "No results found...";
		}

		// set calculated attributes
		request.setAttribute("subtitle", subtitle);
		request.setAttribute(model.toLowerCase(), subList);
		request.setAttribute("pageNums", Paginator.getPageNums());
		request.setAttribute("previous", Paginator.getPreviousPageLink());
		request.setAttribute("next", Paginator.getNextPageLink());
		request.setAttribute("first", Paginator.getFirstPageLink());
		request.setAttribute("last", Paginator.getLastPageLink());
		request.setAttribute("search_term", search_term);
		request.setAttribute("show_param", "block");
		request.setAttribute("showPagination", showPagination);
		request.getRequestDispatcher(model.toLowerCase() + "s.jsp").forward(request, response);
	}

}
