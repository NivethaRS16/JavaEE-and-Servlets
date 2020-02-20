package com.test.filters;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	private ServletContext context;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getRequestURI().startsWith("/HPlusSample/orderHistory") 
				|| req.getRequestURI().startsWith("/HPlusSample/viewProfile"))
		{
			HttpSession session = req.getSession();
			if(session.getAttribute("username")==null)
			{
				req.getRequestDispatcher("/html/login.jsp").forward(req, response);
			}
		}
		chain.doFilter(req, response);
	}
}
