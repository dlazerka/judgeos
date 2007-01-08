package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.judgeos.model.AccountOld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * Process user login.
 * todo move authentication checks to some validator.
 */
public class LogInAction extends Action {
	private HttpServletRequest request;
	private Log log = LogFactory.getFactory().getInstance(LogInForm.class.getName());

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{


		this.request = request;

		String codename = request.getParameter("codename");
		String sql = "SELECT * FROM account WHERE codename = ?";

		DataSource dataSource = getDataSource(request);
		Connection c = dataSource.getConnection();
//		Connection c = ConnectionFactory.getConnection();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, codename);

		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			if (rs.getString("password").equals(request.getParameter("password"))) {
				request.getSession().setAttribute("account", new AccountOld(rs));
				return mapping.findForward("success");
			} else {
				ActionMessage msg = new ActionMessage("errors.account.wrongPassword");
				this.addErrorMessage("password", msg);
			}
		} else {
			ActionMessage msg = new ActionMessage("errors.account.wrongCodename");
			this.addErrorMessage("codename", msg);
		}

		return mapping.getInputForward();
	}

	/**
	 * Adds the error message to both errors containers: Global and my.
	 *
	 * @param property
	 * @param msg
	 */
	private void addErrorMessage(String property, ActionMessage msg) {
		for (String key : new String[]{LogInForm.ERROR_KEY, Globals.ERROR_KEY}
			)
		{
			ActionMessages msgs = (ActionMessages) request.getAttribute(key);
			if (msgs == null) {
				msgs = new ActionErrors();
				msgs.add(property, msg);
				request.setAttribute(key, msgs);
			} else {
				msgs.add(property, msg);
			}
		}
	}
}
