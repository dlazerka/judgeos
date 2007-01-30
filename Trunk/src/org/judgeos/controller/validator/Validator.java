package org.judgeos.controller.validator;

import org.apache.struts.action.ActionMessage;

import java.io.Serializable;

/**
 * Common interface for all of the validators.
 * Parameters (like what and how to validate depends on implementation).
 */
public abstract class Validator implements Serializable {
	protected final ActionMessage errorMessage;


	public Validator(ActionMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Validates our field.
	 *
	 * @param field field to validate
	 * @return is valid
	 */
	public abstract boolean validate(Field field);
}
