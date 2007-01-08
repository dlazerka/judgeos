package org.judgeos.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public class ContestOld {
	private HashMap<String, Object> parameters;

	public ContestOld(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public ContestOld(ResultSet rs) throws SQLException {
		parameters = new HashMap<String, Object>();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			String field = meta.getColumnName(i);
			Object value = rs.getObject(i);
			//Log log = LogFactory.getFactory().getInstance(this.getClass().getName());
			//log.warn(field + "=" + value + "\n");
			parameters.put(field, value);
		}
	}

	public String getDescription() {
		return (String) parameters.get("description");
	}

	public String getCodename() {
		return (String) parameters.get("codename");
	}

	public String getName() {
		return (String) parameters.get("name");
	}

	public Timestamp getStart() {
		return (Timestamp) parameters.get("start");
	}

	public Timestamp getStop() {
		return (Timestamp) parameters.get("stop");
	}

	public String getOwnerCodename() {
		return (String) parameters.get("ownercodename");
	}

	public String getOwnerFirstName() {
		return (String) parameters.get("ownerfirstname");
	}

	public String getOwnerLastName() {
		return (String) parameters.get("ownerlastname");
	}

	public Boolean getIsHot() {
		return (Boolean) parameters.get("ishot");
	}
}
