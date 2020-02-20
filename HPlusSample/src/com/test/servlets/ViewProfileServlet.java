package com.test.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.User;
import com.test.dao.ApplicationDao;

@WebServlet("/viewProfile")
public class ViewProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//get the username from the session
		System.out.println("User name in profile servlet  :"+ request.getSession().getAttribute("username"));
		System.out.println("Conection in profile servlet  :"+ request.getServletContext().getAttribute("connection"));
		Connection connection = (Connection) request.getServletContext().getAttribute("connection");
		String username = (String)request.getSession().getAttribute("username");
		
		//call dao and get profile details
		ApplicationDao dao = new ApplicationDao();
		User user = dao.getProfileDetails(username,connection);
		Map<String,Integer> weightSummary = new HashMap<String,Integer>();
		weightSummary.put("January", 64);
		weightSummary.put("February", 63);
		weightSummary.put("March", 62);
		//store all information in request object
		request.setAttribute("user", user);
		request.setAttribute("weightSummary", weightSummary);
		//forward control to profile jsp
		request.getRequestDispatcher("/html/profile.jsp").forward(request, response);
		
	}


}
