package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.judgeos.model.Contest;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WelcomeAction extends JudgeosAction {
	/**
	 * How much contests should we fetch.
	 */
	private static final int LIMIT = 10;
	
	private static final Log log = LogFactory.getLog(WelcomeAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		super.execute(mapping, form, request, response);
		
		List lastContests = fetchContests(LIMIT);
		request.setAttribute("lastContests", lastContests);

		return mapping.findForward("display");
	}

	/**
	 * Fetchs the most recent contests from DB.
	 * @param limit how much contests to fetch
	 * @return contests
	 */
	private List fetchContests(final int limit) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		session.createCriteria(Contest.class)
			.addOrder(Order.desc("start"))
			.setMaxResults(limit);
		List contests = session.createQuery(
			"from Contest as contest left join fetch contest.owner as owner "
		).list();
		session.close();

		return contests;
	}

}
