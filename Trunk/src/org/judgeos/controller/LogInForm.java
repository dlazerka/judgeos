package org.judgeos.controller;

import org.apache.struts.validator.ValidatorForm;

public class LogInForm extends ValidatorForm {
	private String dataAttached;
	private String email;
	private String password;

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

	public String getDataAttached() {
		return dataAttached;
	}

	public void setDataAttached(String dataAttached) {
		this.dataAttached = dataAttached;
	}
}