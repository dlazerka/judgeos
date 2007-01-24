package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
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

public class WelcomeAction extends Action {
	/**
	 * How much contests should we fetch.
	 */
	private static final int LIMIT = 10;
	
	private static final Log log = LogFactory.getLog(WelcomeAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {
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
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		List contests = session.createCriteria(Contest.class)
			.addOrder(Order.desc("stop"))
			.addOrder(Order.desc("start"))
			.setMaxResults(limit)
			.list();
		session.close();

		return contests;
	}

}
