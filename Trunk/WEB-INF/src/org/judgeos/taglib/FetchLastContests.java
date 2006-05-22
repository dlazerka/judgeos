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


/**
 * Fetchs last contests from DB.
 */
public class FetchLastContests extends SimpleTagSupport {
	private String var;
	private Integer limit;
	private JspContext jspContext;

	public void setJspContext(JspContext jspContext) {
		this.jspContext = jspContext;
	}

	private Contest[] fetchContests() throws IncorrectSetupException, SQLException {
		Connection c = DBFactory.getDbh();
		String sql = "SELECT contest.codename" +
				",contest.name" +
				",description" +
				",owner.firstName AS ownerFirstName" +
				",owner.lastName AS ownerLastName" +
				",owner.codename AS ownerCodename" +
				",publicParticipate" +
				",start" +
				",stop" +
				",contest.createdOn" +
				",CASE WHEN start <= 'now' AND stop >= 'now' THEN true ELSE false END AS isHot " +
				"FROM contest " +
				"LEFT JOIN account AS owner ON contest.owner = owner.id " +
				"ORDER BY start DESC " +
				"LIMIT ?";
		PreparedStatement st = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		st.setInt(1, limit);
		ResultSet rs = st.executeQuery();

		rs.last();
		Contest[] contests = new Contest[rs.getRow()];
		rs.beforeFirst();

		for (int i = 0; rs.next(); i++) {
			contests[i] = new Contest(rs);
		}

		return contests;
	}

	public void doTag() throws JspException, IOException {
		try {
			jspContext.setAttribute(var, this.fetchContests(), PageContext.PAGE_SCOPE);
		} catch (IncorrectSetupException e) {
			throw new JspException(e);
		} catch (SQLException e) {
			throw new JspException(e);
		}
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
