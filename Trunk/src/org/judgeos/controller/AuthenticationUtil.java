package org.judgeos.controller;

import org.judgeos.model.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;

/**
 * Helper methods for user's authentication.
 */
public class AuthenticationUtil {
	private static final Log log = LogFactory.getLog(AuthenticationUtil.class);

	/**
	 * Under this name authentication data is stored in user's session.
	 */
	private static final String ATTRIBUTE_SESSION_ACCOUNT = "account";

	/**
	 * Checks whether a user with given <code>session</code> is authenticated or not.
	 * @param httpSession session which contains(or not) authentication data.
	 * @return is user authenticated
	 */
	public static boolean isAuthenticated(HttpSession httpSession) {
		return httpSession.getAttribute(ATTRIBUTE_SESSION_ACCOUNT) != null;
	}

	/**
	 * Authenticate user with given <code>httpSession</code> as given <code>account</code>
	 * @param account user's account
	 * @param httpSession user's httpSession
	 */
	public static void logInAs(Account account, HttpSession httpSession) {
		httpSession.setAttribute(ATTRIBUTE_SESSION_ACCOUNT, account);
		log.info("Account '"+ account.getId() +"' has logged in to '"+httpSession.getId()+"' session.");
	}

	/**
	 * Removes authentication data from given <code>session</code>.
	 * @param httpSession
	 */
	public static void logOut(HttpSession httpSession) {
		httpSession.removeAttribute(ATTRIBUTE_SESSION_ACCOUNT);
		log.info("Session '"+ httpSession.getId() +"' has been logged out.");
	}
}
