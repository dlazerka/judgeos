package org.judgeos.controller;

import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.judgeos.DBFactory;
import org.judgeos.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Process user registration.
 * todo move authentication checks to some validator.
 */
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


		Connection c = DBFactory.getDbh();
		String sql = "INSERT INTO account(codename, password, firstName, lastName) " +
				"VALUES(?, ?, ?, ?)";

		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, request.getParameter("codename"));
		st.setString(2, request.getParameter("password"));
		st.setString(3, request.getParameter("firstName"));
		st.setString(4, request.getParameter("lastName"));

		st.execute();

		return mapping.findForward("success");
	}

	private void processUnsuccessfulSignUp(ActionMessage msg) {
		for (String key: new String[]{
				SignUpForm.ERROR_KEY, Globals.ERROR_KEY}
		) {
			ActionMessages msgs = (ActionMessages)request.getAttribute(key);
			if (msgs == null) {
				msgs = new ActionErrors();
				msgs.add("codename", msg);
				request.setAttribute(key, msgs);
			}
			else {
				msgs.add("codename", msg);
			}
		}
	}
}
