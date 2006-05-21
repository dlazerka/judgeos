package org.judgeos.model;

import org.judgeos.DBFactory;
import org.judgeos.IncorrectSetupException;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Object> parameters;

	public Account(HashMap<String,Object> parameters) {
		this.parameters = parameters;
	}

	public Account(ResultSet rs) throws SQLException {
		parameters = new HashMap<String, Object>();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			String field = meta.getColumnName(i);
			Object value = rs.getObject(i);
			parameters.put(field, value);
		}
	}

	public HashMap<String,Object> getParameters() {
		return parameters;
	}

	public Object getParameter(String key) {
		return parameters.get(key);
	}

	public String getCodename() {
		return (String) parameters.get("codename");
	}

	void Save() {
//		Statement st = DBConnector.getDbh().createStatement();
	}

	/**
	 * Searches for given codename in table account.
	 * @param codename
	 * @return is codename found
	 * @throws IncorrectSetupException
	 * @throws SQLException
	 */
	public static boolean codenameExists(String codename) throws IncorrectSetupException, SQLException {
		String sql = "SELECT NULL FROM account WHERE codename = ?";
		Connection c = DBFactory.getDbh();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, codename);
		ResultSet rs = st.executeQuery();
		return rs.next();
	}
}
