package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.ApplicationDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		ApplicationDao dao = new ApplicationDao();
		boolean isValid = dao.validateUser(username, password);
				
		if(isValid)
		{
			HttpSession session = req.getSession();			
			session.setAttribute("username", username);
			req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
		}
		else
		{
			String error = "Invalid credentials.Please login again";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
		}
	}
}
