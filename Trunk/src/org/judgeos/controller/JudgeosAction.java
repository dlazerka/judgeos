package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.judgeos.model.Account;
import org.judgeos.model.HibernateUtil;
import org.judgeos.model.JudgeosConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Parent class for all of the actions in the project.
 * Does common operations for each of the actions, and returns an mapping.inputForward.
 */
public class JudgeosAction extends Action {
	private static final Log log = LogFactory.getLog(JudgeosAction.class);
	private static final String ACCOUNT_ATTRIBUTE = "account";
	private static final String JUDGEOS_CONSTANTS_ATTRIBUTE = "judgeosConstants";


	/**
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return inputForward mapping (or null, if there isn't any)
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {

		if (null == request.getSession().getAttribute(ACCOUNT_ATTRIBUTE)) {
			Account guest = loginAsGuest(request);
			log.info(guest);
			request.getSession().setAttribute(ACCOUNT_ATTRIBUTE, guest);
		}

		// todo: move to the startup servlet
		if (null == request.getSession().getAttribute(JUDGEOS_CONSTANTS_ATTRIBUTE)) {
			request.getSession().setAttribute(JUDGEOS_CONSTANTS_ATTRIBUTE, new JudgeosConstants());
		}

		return mapping.getInputForward();
	}

	protected Account loginAsGuest(HttpServletRequest request) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Account account = (Account) session.createCriteria(Account.class).
			add(Restrictions.eq("email", JudgeosConstants.guestEmail))
			.uniqueResult();
		session.close();

		return account;
	}
}