package org.judgeos;

import org.apache.struts.action.*;
import org.judgeos.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.HashMap;

public class LogInAction extends Action {
	private ActionMapping mapping;
	private ActionForm form;
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected static final String ERROR_KEY = "org.judgeos.LogInForm.ERROR_KEY";

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

		String username = request.getParameter("username");
		String sql = "SELECT * FROM account WHERE username = ?";

		Connection c = DB.getDbh();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, username);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			if (rs.getString("password").equals(request.getParameter("password")))
			{
				processSuccessfulLogIn(rs);
				return mapping.findForward("success");
			}
			else {
				ActionMessage msg = new ActionMessage("errors.profile.wrong_password");
				getFormErrors().add("password", msg);
				return mapping.findForward("failure");
			}
		}
		else {
			ActionMessage msg = new ActionMessage("errors.profile.wrong_username");
			getFormErrors().add("username", msg);
			return mapping.findForward("failure");
		}
	}

	private void processSuccessfulLogIn(ResultSet rs) throws SQLException {
		HashMap<String, Object> userData = new HashMap<String, Object>();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			String field = meta.getColumnName(i);
			Object value = rs.getObject(i);
			userData.put(field, value);
		}
		Account account = new Account(userData);
		request.getSession().setAttribute("account", account);
	}

	private ActionMessages getFormErrors() {
		ActionMessages msgs = (ActionMessages)request.getAttribute(ERROR_KEY);
		if (msgs == null) {
			msgs = new ActionErrors();
			request.setAttribute(ERROR_KEY, msgs);
		}
		return msgs;
	}
}
