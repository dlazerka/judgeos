package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.judgeos.controller.validator.MaskValidator;
import org.judgeos.controller.validator.RequiredValidator;
import org.judgeos.controller.validator.TextField;
import org.judgeos.model.Constants;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;

public class SignUpForm extends ActionForm {
	private static final Log log = LogFactory.getLog(SignUpForm.class);
	private TextField email = new TextField(
		"email",
		new RequiredValidator(),
		new MaskValidator(Constants.emailAddressMask)
	);
	private TextField password = new TextField(
		"password",
		new RequiredValidator(),
		new MaskValidator(Constants.passwordMask)
	);
	private TextField fullName = new TextField(
		"fullName",
		new MaskValidator(Constants.fullNameMask)
	);

	public void setEmail(String email) {
		this.email.setValue(email);
	}

	public void setPassword(String password) {
		this.password.setValue(password);
	}

	public String getEmail() {
		return email.getValue();
	}

	public String getPassword() {
		return password.getValue();
	}

	public String getFullName() {
		return fullName.getValue();
	}

	public void setFullName(String fullName) {
		this.fullName.setValue(fullName);
	}


	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest request
	)
	{
		ActionErrors errors = new ActionErrors();
		boolean emailValid = email.validate(errors);
		password.validate(errors);
		fullName.validate(errors);

		if (!emailValid) {
			validateEmailUnique(errors);
		}

		return errors;
	}

	protected void validateEmailUnique(ActionErrors errors) {
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		String queryString =
			"select case count(*) when 0 then true else false end\n" +
				"from Account\n" +
				"where email = :email";

		Query query = session.createQuery(queryString);

		query.setString("email", getEmail());

		if (!(Boolean) query.uniqueResult()) {
			errors.add(email.getProperty(), new ActionMessage("errors.occupied"));
		}

		session.close();
	}
}