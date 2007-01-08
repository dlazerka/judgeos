package org.judgeos.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connects to DB when needed.
 */
public class ConnectionFactory {
	private Log log = LogFactory.getFactory().getInstance(ConnectionFactory.class.getName());
	private static Connection connection;

	public static Connection getConnection() throws SQLException, NamingException {
		if (connection == null) {
			connect();
		}
		return connection;
	}

	/**
	 * Connects to DB using JNDI datasource.
	 * TODO: remove NamingException throw, replace by fatal error
	 */
	private static void connect() throws SQLException, NamingException {
		DataSource dataSource;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		dataSource = (DataSource) envContext.lookup("jdbc/judgeos");

		connection = dataSource.getConnection();

		if (connection == null) {
			throw new NullPointerException("Connection should not be null");
		}
	}
}
