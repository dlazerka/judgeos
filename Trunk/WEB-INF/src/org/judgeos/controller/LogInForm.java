package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

public class LogInForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	protected static final String ERROR_KEY = LogInForm.class.getName() + ".ERROR_KEY";
	private String dataAttached;
	private String email;
	private String password;
	private Log log = LogFactory.getFactory().getInstance(LogInForm.class.getName());


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		request.setAttribute(ERROR_KEY, errors);
		return errors;
	}


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