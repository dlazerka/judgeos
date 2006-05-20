package org.judgeos.model;

import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Contest {
	private HashMap<String, Object> parameters;

	public Contest(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Contest(ResultSet rs) throws SQLException {
		parameters = new HashMap<String, Object>();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			String field = meta.getColumnName(i);
			Object value = rs.getObject(i);
			parameters.put(field, value);
		}
	}

	public String getDescription() {
		return (String) parameters.get("description");
	}

	public void setDescription(String description) {
		parameters.put("description", description);
	}

	public String getCodename() {
		return (String) parameters.get("codename");
	}

	public void setCodename(String codename) {
		parameters.put("codename", codename);
	}

	public String getName() {
		return (String) parameters.get("name");
	}

	public void setName(String name) {
		parameters.put("name", name);
	}
}
