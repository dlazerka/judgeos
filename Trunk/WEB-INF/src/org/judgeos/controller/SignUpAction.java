package org.judgeos.controller;

import org.apache.struts.action.*;
import org.judgeos.model.Account;
import org.judgeos.DB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class SignUpAction extends Action {
	private HttpServletRequest request;

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {
		this.request = request;

		if (Account.codenameExists(request.getParameter("codename"))) {
			ActionMessage msg = new ActionMessage("errors.account.codenameUsed");
			processUnsuccessfulSignUp(msg);
			return mapping.findForward("failure");
		}

		String sql = "INSERT INTO account(codename, password, firstName, lastName) " +
				"VALUES(?, ?, ?, ?)";

		Connection c = DB.getDbh();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, request.getParameter("codename"));
		st.setString(2, request.getParameter("password"));
		st.setString(3, request.getParameter("firstName"));
		st.setString(4, request.getParameter("lastName"));

		st.execute();

		processSuccessfulSignUp();
		return mapping.findForward("success");
	}

	private void processSuccessfulSignUp() {
	}

	private void processUnsuccessfulSignUp(ActionMessage msg) {
		ActionMessages msgs = (ActionMessages)request.getAttribute(SignUpForm.ERROR_KEY);
		if (msgs == null) {
			msgs = new ActionErrors();
		}

		msgs.add("codename", msg);
		request.setAttribute(SignUpForm.ERROR_KEY, msgs);
	}
}
