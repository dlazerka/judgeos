package org.judgeos.model;

import java.io.Serializable;
import java.util.HashMap;

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
}
