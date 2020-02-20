package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Product;
import com.test.beans.User;
import com.test.dao.ApplicationDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String activity = req.getParameter("activity");
		int age = Integer.parseInt(req.getParameter("age"));
		
		User user = new User(username,password,fname,lname,age,activity);
		
		int rows = 0;
		String message = "";
		
		ApplicationDao dao = new ApplicationDao();

			rows = dao.registerUser(user);
		
		if(rows == 0)
		{
			message = "Error occured!!";
		}
		else
		{
			message = "Insertion successful!!";
		}
		
		String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"),message);
		resp.getWriter().write(page);
		
	}	
	
	public String getHTMLString(String filePath,String message) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while((line = reader.readLine())!=null)
		{
			buffer.append(line);
		}
		reader.close();
		String page = buffer.toString();
		page = MessageFormat.format(page, message);
		
		return page;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"),"");
		resp.getWriter().write(page);
	}	
}
	