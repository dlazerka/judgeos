package org.judgeos;

import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

public class LogInForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	protected static final String ERROR_KEY = "org.judgeos.LogInForm.ERROR_KEY";
	private String dataAttached;
	private String codename;
	private String password;
	private Log log = LogFactory.getFactory().getInstance(LogInForm.class.getName());


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

	public String getDataAttached() {
		return dataAttached;
	}

	public void setDataAttached(String dataAttached) {
		this.dataAttached = dataAttached;
	}
}