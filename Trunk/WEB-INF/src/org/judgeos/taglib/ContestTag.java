package org.judgeos.taglib;

import org.judgeos.model.Contest;
import org.judgeos.DB;
import org.judgeos.IncorrectSetupException;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class ContestTag extends SimpleTagSupport {
	private JspContext jspContext;
	private String var;
	private String from;

	private Contest fetchContest(String codename) throws IncorrectSetupException, SQLException {
		if (codename == null) {
			return null;
		}
		else {
			Connection c = DB.getDbh();
			String sql = "SELECT contest.name,owner.firstName||' '||owner.lastName AS owner,"+
					"contest.startTime, contest.stopTime" +
					"FROM contest" +
					"LEFT JOIN account AS owner ON contest.owner = owner.id" +
					"WHERE contest.codename = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, codename);
			ResultSet rs = st.executeQuery();

			if (!rs.next()) {
				return null;
			}
			else {
				return new Contest(rs);
			}
		}
	}

	public void doTag() throws JspException, IOException {
		String codename = (String) jspContext.getAttribute("codename", PageContext.REQUEST_SCOPE);
		try {
			jspContext.setAttribute(var, this.fetchContest(codename), PageContext.PAGE_SCOPE);
		} catch (IncorrectSetupException e) {
			throw new JspException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new JspException(e.getMessage(), e);
		}
	}

	public void setJspContext(JspContext jspContext) {
		this.jspContext = jspContext;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
