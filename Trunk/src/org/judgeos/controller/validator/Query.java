package org.judgeos.controller.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.judgeos.model.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * Validator that executes
 */
public class Query implements Serializable {
	private static final Log log = LogFactory.getLog(Query.class);
	protected static final String QUERY_PARAM = "query";
	private static ActionMessage setupErrorMessage;

	public static boolean validateQuery(
		Object bean,
		ValidatorAction va,
		Field field,
		ActionMessages errors,
		Validator validator,
		HttpServletRequest request
	) throws ValidatorSetupException
	{
		String queryString = field.getVarValue(QUERY_PARAM);

		// if we can't find query param (bad configuration of validator.xml), we must prevent form submittion
		if (queryString == null) {
			errors.add(
				field.getKey(),
				getSetupErrorMessage()
			);

			String logMessage = Query.class.getName() + " validator error: " +
				"'" + QUERY_PARAM + "' parameter is missing for field '" + field.getKey() + "'";

			throw new ValidatorSetupException(logMessage);
		}

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


		Boolean result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			org.hibernate.Query query = session.createQuery(queryString);

			query.setProperties(bean);

			result = (Boolean) query.uniqueResult();

			session.close();
		} catch (HibernateException e) {
			String logMessage = Query.class.getName() + " validator error: Hibernate error while " +
				"validating '" + field.getKey() + "' field and '" + QUERY_PARAM + "' parameter is: " + queryString;

			errors.add(
				field.getKey(),
				getSetupErrorMessage()
			);
			
			throw new ValidatorSetupException(logMessage, e);
		} catch (ClassCastException e) {
			String logMessage = Query.class.getName() + " validator error: Must return Boolean, while " +
				"validating '" + field.getKey() + "' field and '" + QUERY_PARAM + "' parameter is: " + queryString;

			errors.add(
				field.getKey(),
				getSetupErrorMessage()
			);

			throw new ValidatorSetupException(logMessage, e);
		}


		if (!result) {
			errors.add(
				field.getKey(),
				Resources.getActionMessage(validator, request, va, field)
			);
		}

		return result;
	}

	protected static ActionMessage getSetupErrorMessage() {
		if (setupErrorMessage == null) {
			setupErrorMessage = new ActionMessage(
				"error.setup",
				new String[]{Query.class.getName()}
			);
		}
		return setupErrorMessage;
	}
}
