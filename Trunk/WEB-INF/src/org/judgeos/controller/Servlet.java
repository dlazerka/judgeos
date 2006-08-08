package org.judgeos.controller;

import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
	String str;

	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException
	{
		HttpServletRequest	request;
		HttpServletResponse	response;
		try {
			request = (HttpServletRequest) req;
			response = (HttpServletResponse) res;
		} catch (ClassCastException e) {
			throw new ServletException("non-HTTP request or response");
		}
		LogFactory.getFactory().getInstance("my").warn("str2="+request.getContextPath());
		service(request, response);
	}

	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		LogFactory.getFactory().getInstance("my").warn("str="+httpServletRequest.getContextPath());
	}

	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		super.doPost(httpServletRequest, httpServletResponse);
	}
}
