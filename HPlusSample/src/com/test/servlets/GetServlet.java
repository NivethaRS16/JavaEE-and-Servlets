package com.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
@WebServlet(urlPatterns="/getServlet",
			initParams = @WebInitParam(name="URL",value="www.linkedin.com"))
*/
public class GetServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String value = req.getParameter("name");
		ServletConfig config = getServletConfig();
		String cfg = config.getInitParameter("URL");
		ServletContext context = req.getServletContext();
		String ctxt = context.getInitParameter("DBURL");
		String htmlResponse = "<html><h3>Welcome to Servlets!</h3></html>";
		PrintWriter writer = resp.getWriter();
		writer.write(htmlResponse+" "+value+" "+cfg+" "+ctxt);
		
	}

}
