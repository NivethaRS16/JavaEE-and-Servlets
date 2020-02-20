package com.test.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Order;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class OrderHistory
 */
@WebServlet("/orderHistory")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username= (String)(request.getSession().getAttribute("username"));
		Connection connection = (Connection) request.getServletContext().getAttribute("connection");
		ApplicationDao dao = new ApplicationDao();
		List<Order> orders = dao.getOrdersForUser(username, connection);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/html/home.jsp").forward(request, response);
	}


}
