package org.judgeos.taglib;

import org.judgeos.DBFactory;
import org.judgeos.IncorrectSetupException;
import org.judgeos.model.Account;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestLoginTag extends SimpleTagSupport {
	private JspContext jspContext;

	public void setJspContext(JspContext jspContext) {
		this.jspContext = jspContext;
	}

	public void doTag() throws JspException, IOException {
		HttpSession session = (HttpSession) jspContext.getAttribute(PageContext.SESSION);

		String sql = "SELECT * FROM account WHERE codename = ?";

		try {
			Connection c = DBFactory.getDbh();
			PreparedStatement st = c.prepareStatement(sql);

			// TODO place "guest" somewhere in config
			st.setString(1, "guest");

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				session.setAttribute("account", new Account(rs));
			}
			else {
				throw new IncorrectSetupException("Cannot find account \"guest\"");
			}
		} catch (IncorrectSetupException e) {
			throw new JspException(e);
		} catch (SQLException e) {
			throw new JspException(e);
		}
	}
}
