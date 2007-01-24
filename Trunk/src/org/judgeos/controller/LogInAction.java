package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.judgeos.model.Account;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process user login.
 */
public class LogInAction extends Action {
	private Log log = LogFactory.getLog(LogInForm.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{
		Session session = HibernateUtil.getCurrentSession();
		// There is no beginTransaction() cause it must have been already started.
		// todo test it

		Account account = (Account) session.createCriteria(Account.class).
			add(Restrictions.eq("email", request.getParameter("email"))).
			add(Restrictions.eq("password", request.getParameter("password"))).
			uniqueResult();

		if (account == null) {
			String msg = "There is no account entry in DB. " +
				"Maybe transaction was broken " +
				"(email='"+request.getParameter("email")+"')";
			log.error(msg);
			throw new Exception(msg);
		}

		AuthenticationUtil.logInAs(account, request.getSession());

		session.close();

		return mapping.findForward("success");
	}
}
