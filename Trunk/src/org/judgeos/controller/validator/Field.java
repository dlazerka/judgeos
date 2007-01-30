package org.judgeos.controller.validator;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessages;

import java.io.Serializable;

public abstract class Field implements Serializable {
	private ActionForm form;
	private String property;
	private Validator[] validators;

	protected Field(String property, Validator ... validators) {
		this.property = property;
		this.validators = validators;
	}

	public boolean validate(ActionMessages errors) {
		for (Validator validator : validators) {
			if (!validator.validate(this)) {
				addErrorMessage(validator, errors);
				return false;
			}
		}
		return true;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public ActionForm getForm() {
		return form;
	}

	public void setForm(ActionForm form) {
		this.form = form;
	}

	protected void addErrorMessage(Validator validator, ActionMessages errors) {
		if (validator.errorMessage != null) {
			errors.add(getProperty(), validator.errorMessage);
		}
	}
}
