package org.judgeos;

import org.judgeos.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to DB.
 * TODO must be replaced by configured DataSources.
 */
public class DBFactory {
	private static Connection dbh;

	public static Connection getDbh() throws IncorrectSetupException {
		if (dbh == null) {
			connect();
		}
		return dbh;
	}

	private static void connect() throws IncorrectSetupException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new IncorrectSetupException("Cannot find org.postgresql.Driver", e);
		}

		String url = Config.getPgUrl();
		String username = Config.getPgUsername();
		String password = Config.getPgPassword();
		
		try {
			dbh = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new IncorrectSetupException("Cannot connect with params: url=>'" + url +
			"', username=>'" + username + "', password=>'" + password + "'", e);
		}
	}
}
