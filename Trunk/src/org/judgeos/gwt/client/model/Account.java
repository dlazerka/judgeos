package org.judgeos.gwt.client.model;

import java.io.Serializable;

/**
 * Account is a user who deals with the whole project, not the one contest.
 */
public class Account implements Serializable {
	private int id;
	private String email;
	private String fullName;

	public Account() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
