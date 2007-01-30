package org.judgeos.controller.validator;

import org.apache.struts.action.ActionMessage;

public class RequiredValidator extends Validator {
	private static ActionMessage defaultErrorMessage = new ActionMessage("errors.required");

	public RequiredValidator() {
		super(defaultErrorMessage);
	}

	public RequiredValidator(ActionMessage errorMessage) {
		super(errorMessage);
	}

	public boolean validate(Field field) {
		String value = ((TextField) field).getValue();

		return value != null && !"".equals(value);
	}
}
