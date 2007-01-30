package org.judgeos.controller.validator;

public class TextField extends Field {
	private String value;

	public TextField(String property, Validator ... validators) {
		super(property, validators);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
