package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;
import org.hibernate.Session;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;

public class LogInForm extends ValidatorForm {
	private static final Log log = LogFactory.getLog(LogInForm.class);
	private String dataAttached;
	private String email;
	private String password;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
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

	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest request
	)
	{
		ActionErrors errors = super.validate(mapping, request);

		if (!errors.get("email").hasNext()
			&& !errors.get("password").hasNext()
		) {
			validateEmailAndPassword(errors);
		}

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

		org.hibernate.Query query = session.createQuery(queryString);

		query.setString("email", getEmail());
		query.setString("password", getPassword());

		Boolean result = (Boolean) query.uniqueResult();

		if (result == null) {
			errors.add("email", new ActionMessage("errors.doesntexist"));
		}
		else if (!result) {
			errors.add("password", new ActionMessage("errors.wrong"));
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