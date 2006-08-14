package org.judgeos.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;

public class Controller implements org.springframework.web.servlet.mvc.Controller {
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		return new ModelAndView("");
	}
}