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

public class LogInForm extends ActionForm {
	private static final Log log = LogFactory.getLog(LogInForm.class);
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

	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest request
	)
	{
		ActionErrors errors = new ActionErrors();
		if (email.validate(errors) & password.validate(errors)) {
			validateEmailAndPassword(errors);
		}
//		log.warn("kuku");
//		Iterator i = errors.get();
//		while (i.hasNext()) {
//			Object error = i.next();
//			log.warn(error);
//		}
		return errors;
	}

	/**
	 * Validates email-password pair.
	 * Note that if there are no errors the method doesn't finish transaction
	 * cause there will be login action which must find record that we just found.
	 * @param errors where to put errors
	 */
	protected void validateEmailAndPassword(ActionErrors errors) {
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		String queryString =
			"select case password when :password then true else false end\n" +
			"from Account\n" +
			"where email = :email";

		Query query = session.createQuery(queryString);

		query.setString("email", getEmail());
		query.setString("password", getPassword());

		Boolean result = (Boolean) query.uniqueResult();

		if (result == null) {
			errors.add(email.getProperty(), new ActionMessage("errors.doesntexist"));
		}
		else if (!result) {
			errors.add(password.getProperty(), new ActionMessage("errors.wrong"));
		}

		/**
		 * When there aren't errors we MUST NOT finish transaction cause there will
		 * be login action which must find record that we just found.
		 */
		if (!errors.isEmpty()) {
			session.close();
		}
	}
}