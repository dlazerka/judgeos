package org.judgeos.model;

import java.util.HashMap;

public class Contest {
	private HashMap<String, Object> parameters;

	public Contest(HashMap<String, Object> parameters) {

		this.parameters = parameters;
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
