package org.judgeos.controller.validator;

import org.apache.struts.action.ActionMessage;

import java.util.regex.Pattern;

public class MaskValidator extends Validator {
	private static ActionMessage defaultErrorMessage = new ActionMessage("errors.invalidSyntax");
	private final Pattern pattern;

	public MaskValidator(Pattern pattern) {
		this(pattern, defaultErrorMessage);
	}

	public MaskValidator(String patternString) {
		this(Pattern.compile(patternString), defaultErrorMessage);
	}

	public MaskValidator(String patternString, ActionMessage errorMessage) {
		this(Pattern.compile(patternString), errorMessage);
	}

	public MaskValidator(Pattern pattern, ActionMessage errorMessage) {
		super(errorMessage);
		this.pattern = pattern;
	}

	public boolean validate(Field field) {
		return pattern.matcher(((TextField) field).getValue()).matches();
	}
}
