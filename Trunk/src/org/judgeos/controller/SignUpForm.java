package org.judgeos.controller;

import org.apache.struts.validator.ValidatorForm;

public class SignUpForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private String fullName;
	private String dataAttached;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setDataAttached(String dataAttached) {
		this.dataAttached = dataAttached;
	}

	public String getDataAttached() {
		return dataAttached;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}