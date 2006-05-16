package org.judgeos;

import org.apache.struts.action.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.judgeos.model.Account;
import org.postgresql.util.PSQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.HashMap;

public class SignUpAction extends Action {
	private ActionMapping mapping;
	private ActionForm form;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {
		this.mapping = mapping;
		this.form = form;
		this.request = request;
		this.response = response;

		String sql = "INSERT INTO account(username, password) " +
				"VALUES(?, ?)";

		Connection c = DB.getDbh();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, request.getParameter("username"));
		st.setString(2, request.getParameter("password"));

		try {
			st.execute();
		}
		catch(PSQLException e) {
			if(e.getServerErrorMessage().equals(
					"ERROR: duplicate key violates unique constraint \"account_username_idx\""
			)) {
				ActionMessage msg = new ActionMessage("errors.profile.username_used");
				processUnsuccessfulSignUp(msg);
				return mapping.findForward("failure");
			}
			else {
				throw e;
			}
		}

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

		msgs.add("username", msg);
		request.setAttribute(SignUpForm.ERROR_KEY, msgs);
	}
}
