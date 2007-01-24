package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.judgeos.model.Contest;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContestAction extends Action {
	private static final Log log = LogFactory.getLog(ContestAction.class);
	private static final String CONTEST_ID_PARAM_NAME = "id";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{
		try {
			Integer contestId = Integer.valueOf(request.getParameter(CONTEST_ID_PARAM_NAME));

			if (contestId == null) {
				doNotFoundContest(request.getParameter(CONTEST_ID_PARAM_NAME), response);
				return mapping.findForward("notFoundContest");
			}

			Contest contest = fetchContest(contestId);

			if (contest == null) {
				doNotFoundContest(request.getParameter(CONTEST_ID_PARAM_NAME), response);
				return mapping.findForward("notFoundContest");
			}

			request.setAttribute("contest", contest);
		} catch (NumberFormatException e) {
			doNotFoundContest(request.getParameter(CONTEST_ID_PARAM_NAME), response);
			return mapping.findForward("notFoundContest");
		}

		return mapping.findForward("success");
	}

	private void doNotFoundContest(
		Object contestId, HttpServletResponse response
	) throws IOException
	{
		response.sendError(404, String.valueOf(contestId));
	}

	/**
	 * Fetchs a contest by it's id.
	 *
	 * @param contestId which contest to fetch
	 * @return contest or null if cannot find
	 */
	private Contest fetchContest(Integer contestId) {
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		return (Contest) session.get(Contest.class, contestId);
	}
}
