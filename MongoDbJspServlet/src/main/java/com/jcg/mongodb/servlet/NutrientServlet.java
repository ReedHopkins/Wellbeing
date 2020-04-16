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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve all nutrients in database
		DatabaseSingleton.getInstance();
		ArrayList<Nutrient> nutrients = DatabaseSingleton.getNutrients();
		int size = nutrients.size();

		// begin pagination calculations
		String spageid = request.getParameter("page");
		if (spageid == null) {
			spageid = "1";
		}
		String search_term = request.getParameter("search_term");
		String subtitle = "All Nutrients (Page " + spageid + "):";

		if (search_term == null || "".equals(search_term)) {
			
			Paginator Paginator = new Paginator("NutrientServlet", spageid, size, 10);
			ArrayList<Nutrient> subList = new ArrayList<Nutrient>(nutrients.subList(Paginator.getStartIndex(), Paginator.getEndIndex()));

			// set calculated attributes
			request.setAttribute("subtitle", subtitle);
			request.setAttribute("nutrient", subList);
			request.setAttribute("pageNums", Paginator.getPageNums());
			request.setAttribute("previous", Paginator.getPreviousPageLink());
			request.setAttribute("next", Paginator.getNextPageLink());
			request.setAttribute("first", Paginator.getFirstPageLink());
			request.setAttribute("last", Paginator.getLastPageLink());
			request.getRequestDispatcher("/pages/nutrients.jsp").forward(request, response);

		} else {
			doPost(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Reading post parameters from the request
		String search_param = request.getParameter("search_term");
		search_param = search_param.toLowerCase();
		String spageid = request.getParameter("page");
		if (spageid == null) {
			spageid = "1";
		}
		String subtitle = "Search Results (Page " + spageid + "):";

		// Search for matched nutrients
		DatabaseSingleton.getInstance();
		ArrayList<Nutrient> nutrients = DatabaseSingleton.searchNutrients(search_param);
		int size = nutrients.size();

		Paginator Paginator = new Paginator("NutrientServlet", spageid, size, 10);
		Paginator.setSearchTerm(search_param);
		
		ArrayList<Nutrient> subList = new ArrayList<Nutrient>(nutrients.subList(Paginator.getStartIndex(), Paginator.getEndIndex()));

		String showPagination = "default";
		if (subList.size() < 1) {
			showPagination = "none";
			subtitle = "No results found...";
		}

		// set calculated attributes
		request.setAttribute("subtitle", subtitle);
		request.setAttribute("nutrient", subList);
		request.setAttribute("pageNums", Paginator.getPageNums());
		request.setAttribute("previous", Paginator.getPreviousPageLink());
		request.setAttribute("next", Paginator.getNextPageLink());
		request.setAttribute("first", Paginator.getFirstPageLink());
		request.setAttribute("last", Paginator.getLastPageLink());
		request.setAttribute("search_term", search_param);
		request.setAttribute("showPagination", showPagination);
		request.getRequestDispatcher("/pages/nutrients.jsp").forward(request, response);
	}

}
