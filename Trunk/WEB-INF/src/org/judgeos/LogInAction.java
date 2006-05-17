package org.judgeos;

import org.apache.struts.action.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.judgeos.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.HashMap;

public class LogInAction extends Action {
	private HttpServletRequest request;
	private Log log = LogFactory.getFactory().getInstance(LogInForm.class.getName());

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {
		this.request = request;

		if (request.getParameter("codename") == null) {
			return mapping.getInputForward();
		}

		String codename = request.getParameter("codename");
		String sql = "SELECT * FROM account WHERE codename = ?";

		Connection c = DB.getDbh();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, codename);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			if (rs.getString("password").equals(request.getParameter("password")))
			{
				processSuccessfulLogIn(rs);
				return mapping.findForward("success");
			}
			else {
				ActionMessage msg = new ActionMessage("errors.account.wrongPassword");
				getFormErrors().add("password", msg);
			}
		}
		else {
			ActionMessage msg = new ActionMessage("errors.account.wrongCodename");
			getFormErrors().add("codename", msg);
		}

		return mapping.getInputForward();
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

	/**
	 * Looks at request attribute specified by LogInForm.ERROR_KEY key a creates new if necessary.
	 * @return this.request.getAttribute(LogInForm.ERROR_KEY)
	 */
	private ActionMessages getFormErrors() {
		ActionMessages msgs = (ActionMessages)request.getAttribute(LogInForm.ERROR_KEY);
		if (msgs == null) {
			msgs = new ActionErrors();
			request.setAttribute(LogInForm.ERROR_KEY, msgs);
		}
		return msgs;
	}
}
