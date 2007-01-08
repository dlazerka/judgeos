package org.judgeos.controller.validator;

import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMessages;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class Query implements Serializable {
	private static final Log log = LogFactory.getLog(Query.class);

	public static boolean validateValidWhen(
	    Object bean,
	    ValidatorAction va,
	    Field field,
	    ActionMessages errors,
	    Validator validator,
	    HttpServletRequest request
	) {
		String query = field.getVarValue("query");
		log.info(query);
		log.info(bean.toString());
		log.info(va.toString());
		log.info(field.toString());
		log.info(errors.toString());
		log.info(validator.toString());
		log.info(request.toString());
		return false;
	}
}
