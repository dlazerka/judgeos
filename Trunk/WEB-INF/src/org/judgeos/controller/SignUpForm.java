package org.judgeos.controller;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

public class SignUpForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String codename;
	private String password;
	private String firstName;
	private String lastName;
	private String dataAttached;

	protected static final String ERROR_KEY = "org.judgeos.controller.SignUpForm.ERROR_KEY";

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		request.setAttribute(ERROR_KEY, errors);
		return errors;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodename() {
		return codename;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}