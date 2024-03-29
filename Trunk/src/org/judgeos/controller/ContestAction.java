package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.judgeos.model.Contest;
import org.judgeos.model.HibernateUtil;
import org.hibernate.Session;

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

			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			Contest contest = (Contest) session.get(Contest.class, contestId);

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
		response.sendError(HttpServletResponse.SC_NOT_FOUND, String.valueOf(contestId));
	}
}
