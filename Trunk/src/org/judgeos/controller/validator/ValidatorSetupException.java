package org.judgeos.controller.validator;

public class ValidatorSetupException extends Exception {
	public ValidatorSetupException() {
		super();
	}

	public ValidatorSetupException(String message) {
		super(message);
	}

	public ValidatorSetupException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorSetupException(Throwable cause) {
		super(cause);
	}
}
