package org.judgeos.model;

import org.judgeos.DB;
import org.judgeos.IncorrectSetupException;
import org.postgresql.util.PSQLException;
import org.apache.struts.action.ActionMessage;

import java.io.Serializable;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: uzver
 * Date: 13.05.2006
 * Time: 20:14:57
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Object> parameters;

	public Account(HashMap<String,Object> parameters) {
		this.parameters = parameters;
	}

	public HashMap<String,Object> getParameters() {
		return parameters;
	}

	public Object getParameter(String key) {
		return parameters.get(key);
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
		Connection c = DB.getDbh();
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, codename);
		ResultSet rs = st.executeQuery();
		return rs.next();
	}
}
