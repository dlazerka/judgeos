package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.judgeos.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process user login.
 */
public class ParticipateAction extends Action {
	private Log log = LogFactory.getLog(ParticipateAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{
		Session session = HibernateUtil.getCurrentSession();
		// There is no beginTransaction() cause it must have been already started.
		ParticipateForm participateForm = (ParticipateForm) form;
		Contest contest = participateForm.fetchedContest;

		Member contestMember;
		if (contest.getPublicParticipate()) {
			if (AuthenticationUtil.isGuestAccount(request.getSession())) {
				ActionForward returnForward = new ActionForward(
					request.getServletPath() + "?" + request.getQueryString(),
					true
				);
				MustReturnStackUtil.push(returnForward, request.getSession());
				return mapping.findForward("login");
			}

			Account account = AuthenticationUtil.getAuthenticatedAs(request.getSession());

			contestMember = (Member) session.createCriteria(Member.class)
				.add(Restrictions.eq("contest", contest))
				.add(Restrictions.eq("responsibleAccount", account))
				.uniqueResult();

			if (contestMember == null) {
				contestMember = new Member();
				contestMember.setContest(contest);
				contestMember.setName(account.getFullName());
				contestMember.setPassword(null);
				contestMember.setResponsibleAccount(account);
				contestMember.setRole(MemberRole.getParticipantRole());
				session.save(contestMember);
				session.getTransaction().commit();

				log.info("Account '" + account.getId() + "' now participate in contest '" + contest.getId() + "'");
			}
		} else {
			contestMember = participateForm.fetchedContestMember;
			log.info("Contest member '" + contestMember.getId() + "' now participate in contest '" + contest.getId() + "'");
		}

		AuthenticationUtil.becomeContestMemberAs(contestMember, request.getSession());

		return mapping.findForward("success");
	}
}
