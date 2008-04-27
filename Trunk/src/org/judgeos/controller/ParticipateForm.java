package org.judgeos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.judgeos.controller.validator.MaskValidator;
import org.judgeos.controller.validator.RequiredValidator;
import org.judgeos.controller.validator.TextField;
import org.judgeos.model.Constants;
import org.judgeos.model.Contest;
import org.judgeos.model.Member;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;

public class ParticipateForm extends ActionForm {
	private static final Log log = LogFactory.getLog(ParticipateForm.class);
	private static final String CONTEST_ID_PARAM_NAME = "id";
	private TextField password = new TextField(
		"password",
		new RequiredValidator(),
		new MaskValidator(Constants.passwordMask)
	);

	/**
	 * A Contest that is fetched during form validation. Will be used in action to improve performance.
	 */
	protected transient Contest fetchedContest;

	/**
	 * A Member that is fetched during form validation. Will be used in action to improve performance.
	 */
	protected transient Member fetchedContestMember;

	public void setPassword(String password) {
		this.password.setValue(password);
	}

	public String getPassword() {
		return password.getValue();
	}

	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest request
	)
	{
		ActionErrors errors = new ActionErrors();

		Integer contestId;
		try {
			contestId = Integer.valueOf(request.getParameter(CONTEST_ID_PARAM_NAME));
		} catch (NumberFormatException e) {
			doNotFoundContest(request, mapping, errors);
			return errors;
		}

		if (!errors.isEmpty()) {
			return errors;
		}

		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		fetchedContest = (Contest) session.get(Contest.class, contestId);

		if (fetchedContest == null) {
			doNotFoundContest(request, mapping, errors);
			return errors;
		}
		if (!fetchedContest.getPublicParticipate()) {
			log.warn(fetchedContest.getPublicParticipate());
			password.validate(errors);

			if (!errors.get("password").hasNext()) {
				fetchedContestMember = (Member) session.createCriteria(Member.class)
					.add(Restrictions.eq("contest", fetchedContest))
					.add(Restrictions.eq("password", getPassword()))
					.uniqueResult();

				if (fetchedContestMember == null) {
					errors.add(password.getProperty(), new ActionMessage("errors.wrong"));
				}
			}
		}

		if (!errors.isEmpty()) {
			// Add query string to the input mapping
			String newInput = mapping.getInput() + request.getQueryString();
			mapping.setInput(newInput);
		}
		return errors;
	}

	/**
	 * Does actions in case we have not found contest.
	 * @param request
	 * @param mapping
	 * @param errors
	 */
	private void doNotFoundContest(HttpServletRequest request, ActionMapping mapping, ActionMessages errors) {
		String contestId = (String) request.getAttribute(CONTEST_ID_PARAM_NAME);

		// Add error message.
		ActionMessage msg = new ActionMessage("errors.contest.notFoundContest", contestId);
		errors.add("contestId", msg);
	}

	/**
	 * Validates password. Note that if there are no errors the method doesn't finish transaction cause there will be login
	 * action which must find record that we just found.
	 *
	 * @param errors where to put errors
	 */
	protected void validatePassword(ActionErrors errors) {
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();

		String queryString =
			"select case password when :password then true else false end\n" +
				"from Member\n" +
				"where email = :email";

		Query query = session.createQuery(queryString);

		query.setString("password", getPassword());

		Boolean result = (Boolean) query.uniqueResult();

		if (result == null) {
			errors.add("email", new ActionMessage("errors.doesntexist"));
		} else if (!result) {
			errors.add("password", new ActionMessage("errors.wrong"));
		}

		/**
		 * When there aren't errors we MUST NOT finish transaction cause there will
		 * be login action which must find record that we just found.
		 if (!errors.isEmpty()) {
		 session.close();
		 }
		 */
	}
}
