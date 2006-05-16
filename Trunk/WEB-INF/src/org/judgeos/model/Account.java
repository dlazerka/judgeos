package org.judgeos.model;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: uzver
 * Date: 13.05.2006
 * Time: 20:14:57
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public Account(HashMap<String,Object> data) {
		for (String field: data.keySet()) {
			if (field.equals("username")) {
				this.setUsername((String) data.get(field));
			}
			else if (field.equals("password")) {
				this.setPassword((String) data.get(field));
			}
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	void Save() {
//		Statement st = DBConnector.getDbh().createStatement();
	}
}
