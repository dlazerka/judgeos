package org.judgeos.controller;

import org.hibernate.Session;
import org.judgeos.model.Account;
import org.judgeos.model.Constants;
import org.judgeos.model.HibernateUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActionServlet extends org.apache.struts.action.ActionServlet {
//	protected static Log log = LogFactory.getLog(ActionServlet.class);

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
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		HttpSession httpSession = request.getSession();
		if (!AuthenticationUtil.isAuthenticated(httpSession)) {
			AuthenticationUtil.logInAs(Account.getGuestInstance(), httpSession);
		}

		super.process(request, response);

		Session hibSession = HibernateUtil.getCurrentSession();
		synchronized (hibSession) {// todo consider moving to an filter
			if (hibSession.isOpen()) {
				hibSession.close();
			}
		}
	}
}
