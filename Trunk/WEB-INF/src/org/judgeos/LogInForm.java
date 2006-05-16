package org.judgeos;

import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

public class LogInForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_KEY = "org.judgeos.LogInForm.ERROR_KEY";

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		request.setAttribute(ERROR_KEY, errors);
		return errors;
	}

	public String username;
	public String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}