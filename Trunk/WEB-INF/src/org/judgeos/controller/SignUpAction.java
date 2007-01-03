package org.judgeos.controller;

import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.judgeos.model.ConnectionFactory;
import org.judgeos.model.AccountOld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Process user registration.
 */
public class SignUpAction extends Action {


	/**
	 * Checks for existence of the same codename in DB and, if so, returns 'failure' forward,
	 * else adds the record to the 'account' table and returns 'success' forward. Note that
	 * actions to automatically log the user in must be done on 'success' forward.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 'failure' or 'success' forwards
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception
	{

		if (AccountOld.codenameExists(request.getParameter("codename"))) {
			ActionMessage msg = new ActionMessage("errors.account.codenameUsed");
			addErrorMessage(msg, request);
			return mapping.findForward("failure");
		}

		addAccount(request);

		return mapping.findForward("success");
	}


	/**
	 * Inserts account row to the 'account' table basing on request parameters data.
	 *
	 * @param request
	 * @throws SQLException
	 */
	private void addAccount(HttpServletRequest request) throws SQLException, NamingException {
		Connection c = ConnectionFactory.getConnection();
		String sql = "INSERT INTO account(codename, password, firstName, lastName) " +
			"VALUES(?, ?, ?, ?)";

		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, request.getParameter("codename"));
		st.setString(2, request.getParameter("password"));
		st.setString(3, request.getParameter("firstName"));
		st.setString(4, request.getParameter("lastName"));

		st.execute();
	}


	/**
	 * Puts given error to global and SignUpForm errors collections.
	 *
	 * @param msg
	 * @param request
	 */
	private void addErrorMessage(ActionMessage msg, HttpServletRequest request) {
		for (String key : new String[]{
			SignUpForm.ERROR_KEY, Globals.ERROR_KEY}
			)
		{
			ActionMessages msgs = (ActionMessages) request.getAttribute(key);
			if (msgs == null) {
				msgs = new ActionErrors();
				msgs.add("codename", msg);
				request.setAttribute(key, msgs);
			} else {
				msgs.add("codename", msg);
			}
		}
	}
}
