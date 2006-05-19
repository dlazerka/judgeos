package org.judgeos.model;

import org.judgeos.DB;
import org.judgeos.IncorrectSetupException;

import java.util.HashMap;
import java.sql.*;

public class LastContests {
	private Contest[] contests;

	public LastContests() throws IncorrectSetupException, SQLException {
		Contest[] contests;
		String sql = "SELECT * FROM contest ORDER BY createdOn DESC LIMIT 10";

		Statement st = DB.getDbh().createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);

		rs.last();
		contests = new Contest[rs.getRow()];
		rs.beforeFirst();

		for (int i = 0; rs.next(); i++) {
			HashMap<String, Object> contestData = new HashMap<String, Object>();
			ResultSetMetaData meta = rs.getMetaData();
			for (int j = 1; j <= meta.getColumnCount(); j++) {
				String field = meta.getColumnName(j);
				Object value = rs.getObject(j);
				contestData.put(field, value);
			}
			contests[i] = new Contest(contestData);
		}

		this.contests = contests;
	}

	public Contest[] getContests() {
		return this.contests;
	}
}
