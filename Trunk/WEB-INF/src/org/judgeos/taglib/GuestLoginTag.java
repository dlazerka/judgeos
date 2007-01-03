package org.judgeos.taglib;

import org.judgeos.model.ConnectionFactory;
import org.judgeos.model.AccountOld;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GuestLoginTag extends SimpleTagSupport {
	private JspContext jspContext;

	public void setJspContext(JspContext jspContext) {
		this.jspContext = jspContext;
	}

	public void doTag() throws JspException, IOException {
		HttpSession session = (HttpSession) jspContext.getAttribute(PageContext.SESSION);

		String sql = "SELECT * FROM account WHERE codename = ?";

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement st = c.prepareStatement(sql);

			// TODO place "guest" somewhere in config
			st.setString(1, "guest");

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				session.setAttribute("account", new AccountOld(rs));
			} else {
				throw new IllegalStateException("Cannot find account \"guest\"");
			}
		} catch (Exception e) {
			throw new JspException(e);
		}
	}
}
