package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.judgeos.model.Account;
import org.judgeos.model.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActionServlet extends org.apache.struts.action.ActionServlet {
	protected static Log log = LogFactory.getLog(ActionServlet.class);

	/**
	 * Among super actions puts Constants instance to the servlet context.
	 *
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		super.init();

		// Put Constants instance to the servlet context
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("CONSTANTS", Constants.getInstantce());
	}


	/**
	 * If user isn't authenticated, login his as guest.
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		if ( ! AuthenticationUtil.isAuthenticated(session)) {
			AuthenticationUtil.logInAs(Account.getGuestInstance(), session);
		}

		super.process(request, response);
	}
}
