package org.judgeos.controller;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.judgeos.model.Account;
import org.judgeos.model.HibernateUtil;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Process user registration.
 */
public class SignUpAction extends Action {
	private Log log = LogFactory.getLog(SignUpAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{
		Account account = createAccountUsingParams(request);

		insertAccount(account);

		AuthenticationUtil.logInAs(account, request.getSession());

		return mapping.findForward("success");
	}

	private Account createAccountUsingParams(HttpServletRequest request) {
		Account account = new Account();
		account.setEmail(request.getParameter("email"));
		account.setPassword(request.getParameter("password"));
		account.setFullName(request.getParameter("fullName"));
		return account;
	}

	private void insertAccount(Account account)
		throws SQLException, NamingException
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		session.save(account);
		session.getTransaction().commit();

		log.info("New account '"+ account.getId() +"' has signed up.");
	}
}
