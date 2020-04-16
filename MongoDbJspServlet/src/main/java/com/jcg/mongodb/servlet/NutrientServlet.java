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

		// begin pagination calculations
		String spageid = request.getParameter("page");
		String search_term = request.getParameter("search_term");

		if (search_term == null || "".equals(search_term)) {

			if (spageid == null)
				spageid = "1";

			int pageId = Integer.parseInt(spageid);
			int total = 10;

			int start = Util.getStartIndex(pageId, total);
			int last = Util.getLastPage(nutrients.size(), total);
			int end = Util.getEndIndex(start, nutrients.size(), total);
			ArrayList<Integer> pageNums = Util.getPaginatorNums(pageId, last);

			ArrayList<Nutrient> subList = new ArrayList<Nutrient>(nutrients.subList(start, end));

			String previous = "#";
			if (pageId > 1)
				previous = "NutrientServlet?page=" + Integer.toString(pageId - 1);
			String next = "#";
			if (pageId < last)
				next = "NutrientServlet?page=" + Integer.toString(pageId + 1);

			// set calculated attributes
			request.setAttribute("subtitle", "All Nutrients:");
			request.setAttribute("nutrient", subList);
			request.setAttribute("pageNums", pageNums);
			request.setAttribute("previous", previous);
			request.setAttribute("next", next);
			request.setAttribute("first", "NutrientServlet?page=1");
			request.setAttribute("last", "NutrientServlet?page=" + last);
			request.getRequestDispatcher("/pages/nutrients.jsp").forward(request, response);

		} else {
			System.out.println(search_term);
			doPost(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Reading post parameters from the request
		String search_param = request.getParameter("search_term");
		String spageid = request.getParameter("page");

		search_param = search_param.toLowerCase();
		String subtitle = "Search Results (Page " + spageid + "):";

		// Search for matched nutrients
		DatabaseSingleton.getInstance();
		ArrayList<Nutrient> nutrients = DatabaseSingleton.searchNutrients(search_param);

		// begin pagination calculations
		if (spageid == null)
			spageid = "1";

		int pageId = Integer.parseInt(spageid);
		int total = 10;

		int start = Util.getStartIndex(pageId, total);
		int last = Util.getLastPage(nutrients.size(), total);
		int end = Util.getEndIndex(start, nutrients.size(), total);
		ArrayList<Integer> pageNums = Util.getPaginatorNums(pageId, last);

		ArrayList<Nutrient> subList = new ArrayList<Nutrient>(nutrients.subList(start, end));

		String previous = "#";
		if (pageId > 1)
			previous = "NutrientServlet?search_term=" + search_param + "&page=" + Integer.toString(pageId - 1);
		String next = "#";
		if (pageId < last)
			next = "NutrientServlet?search_term=" + search_param + "&page=" + Integer.toString(pageId + 1);

		String showPagination = "default";
		if (subList.size() < 1) {
			showPagination = "none";
			subtitle = "No results found...";
		}

		// set calculated attributes
		request.setAttribute("subtitle", subtitle);
		request.setAttribute("nutrient", subList);
		request.setAttribute("pageNums", pageNums);
		request.setAttribute("previous", previous);
		request.setAttribute("next", next);
		request.setAttribute("first", "NutrientServlet?search_term=" + search_param + "&page=1");
		request.setAttribute("last", "NutrientServlet?search_term=" + search_param + "&page=" + last);
		request.setAttribute("search_term", search_param);
		request.setAttribute("showPagination", showPagination);
		request.getRequestDispatcher("/pages/nutrients.jsp").forward(request, response);
	}

}
