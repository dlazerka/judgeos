package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.judgeos.model.ContestMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContestCurrentAction extends Action {
	private static final Log log = LogFactory.getLog(ContestCurrentAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{
		ContestMember contestMember = AuthenticationUtil.getContestMemberAs(request.getSession());
		if (contestMember == null) {
			return mapping.findForward("isNotContestMember");
		}
		return mapping.findForward("success");
	}
}
