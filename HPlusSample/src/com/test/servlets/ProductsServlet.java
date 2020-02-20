package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/addProducts")
public class ProductsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Connection connection = (Connection) getServletContext().getAttribute("connection");
		List<String> productName = (ArrayList<String>) session.getAttribute("noofproducts");
		
		if(productName == null)
		{
			productName = new ArrayList<String>();
		}
		
		if(req.getParameter("product") != null)
		{
			productName.add(req.getParameter("product"));
		}
		
		session.setAttribute("noofproducts", productName);
		
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(session.getAttribute("search").toString(), connection);
		
		req.setAttribute("products", products);
		
		req.getRequestDispatcher("/html/search1.jsp").forward(req, res);
		/*
		HttpSession session = request.getSession();
		List<String> productNames = (ArrayList<String>)(session.getAttribute("noofproducts"));
		
		if(productNames==null){
			productNames=new ArrayList<String>();
		}
		
		System.out.println("product name== "+request.getParameter("product"));
		
		if (request.getParameter("product") != null ) {
			
			productNames.add(request.getParameter("product"));			
		}
		
		
		
		ApplicationDao searchDao = new ApplicationDao();
		session.setAttribute("noofproducts", productNames);
		List<Product> products = new ArrayList<>();
		
	    products = searchDao.searchProducts(session.getAttribute("searchCriteria").toString());
		
		System.out.println("fresh fetch:::: "+products.size());
		request.setAttribute("products", products);
		request.getRequestDispatcher("/html/search.jsp").forward(request, response);

		*/
	}

	
	private String getHTMLString(String filePath, List<Product> products, int sizeOfCart) throws IOException {
		BufferedReader template = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuilder builder = new StringBuilder();
		while((line=template.readLine())!=null){
			builder.append(line);
		}
		String page = builder.toString();
		page = MessageFormat.format(page, 
				products.get(0).getProductImgPath(),
				products.get(1).getProductImgPath(),
				products.get(2).getProductImgPath(),
				products.get(0).getProductName(),
				products.get(1).getProductName(),
				products.get(2).getProductName(),sizeOfCart);
				
		return page;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
