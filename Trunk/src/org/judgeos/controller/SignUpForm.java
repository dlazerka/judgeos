package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;
import org.hibernate.Query;
import org.hibernate.Session;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;

public class SignUpForm extends ValidatorForm {
	private static final Log log = LogFactory.getLog(SignUpForm.class);
	private String email;
	private String password;
	private String fullName;
	private String dataAttached;

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

	public void setDataAttached(String dataAttached) {
		this.dataAttached = dataAttached;
	}

	public String getDataAttached() {
		return dataAttached;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest request
	)
	{
		ActionErrors errors = super.validate(mapping, request);

		if (!errors.get("email").hasNext()) {
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

		if (!(Boolean)query.uniqueResult()) {
			errors.add("email", new ActionMessage("errors.occupied"));
		}

		session.close();
	}
}