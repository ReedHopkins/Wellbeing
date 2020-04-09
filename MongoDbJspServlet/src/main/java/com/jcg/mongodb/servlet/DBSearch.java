package com.jcg.mongodb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchServlet")
public class DBSearch extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		handleRequest(req, resp);
	}

	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Reading post parameters from the request
		String param1 = req.getParameter("search_id");

//		// Checking for null and empty values
//		if(param1 == null || "".equals(param1)) {
//			req.setAttribute("error_message", "Please enter a food");
//			req.getRequestDispatcher("/search.jsp").forward(req, resp);
//		} else {
//
//			boolean isFoodFound = Util.searchFood(param1);
//			if(isFoodFound) {				
//				req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
//			} else {
//				req.setAttribute("error_message", "You are not an authorised user. Please check with administrator.");
//				req.getRequestDispatcher("/index.jsp").forward(req, resp);
//			}	
//		}		
	}
}