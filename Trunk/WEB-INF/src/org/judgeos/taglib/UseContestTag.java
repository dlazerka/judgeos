package org.judgeos.taglib;

import org.judgeos.DBFactory;
import org.judgeos.IncorrectSetupException;
import org.judgeos.model.Contest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UseContestTag extends SimpleTagSupport {
	private String var;
	private JspContext jspContext;

	public void setJspContext(JspContext jspContext) {
		this.jspContext = jspContext;
	}

	private Contest fetchContest(String codename) throws IncorrectSetupException, SQLException {
		if (codename == null) {
			return null;
		}
		else {
			Connection c = DBFactory.getDbh();
			String sql = "SELECT contest.name, start, stop, description, "+
					"owner.firstName AS ownerFirstName, owner.lastName AS ownerLastName, " +
					"owner.codename AS ownerCodename " +
					"FROM contest " +
					"LEFT JOIN account AS owner ON contest.owner = owner.id " +
					"WHERE contest.codename = ? ";
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
		HttpServletRequest request = (HttpServletRequest) jspContext.getAttribute(PageContext.REQUEST);
		String codename = request.getParameter("codename");
		try {
			jspContext.setAttribute(var, this.fetchContest(codename), PageContext.PAGE_SCOPE);
		} catch (IncorrectSetupException e) {
			throw new JspException(e);
		} catch (SQLException e) {
			throw new JspException(e);
		}
	}

	public void setVar(String var) {
		this.var = var;
	}
}
