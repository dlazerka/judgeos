package org.judgeos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

public class SignUpForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	public String username;
	public String password;
	
	private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

	protected static final String ERROR_KEY = "org.judgeos.SignUpForm.ERROR_KEY";

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		request.setAttribute(ERROR_KEY, errors);
		return errors;
	}

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