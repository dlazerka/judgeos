package org.judgeos.controller;

import org.judgeos.model.Account;
import org.judgeos.model.ContestMember;
import org.judgeos.model.Constants;
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
	private static final String ATTRIBUTE_SESSION_CONTESTMEMBER = "contestMember";

	/**
	 * Checks whether a user with given <code>session</code> is authenticated or not.
	 * @param httpSession session which contains(or not) authentication data.
	 * @return is user authenticated
	 */
	public static boolean isAuthenticated(HttpSession httpSession) {
		return getAuthenticatedAs(httpSession) != null;
	}

	/**
	 * Returns account data for user with given <code>session</code>.
	 * @param httpSession session which contains(or not) authentication data.
	 * @return is user authenticated
	 */
	public static Account getAuthenticatedAs(HttpSession httpSession) {
		return (Account) httpSession.getAttribute(ATTRIBUTE_SESSION_ACCOUNT);
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
	 * Determines whether current user is authenticated as 'guest'.
	 * @param httpSession user's httpSession
	 * @return is current user authenticated as guest
	 */
	public static boolean isGuestAccount(HttpSession httpSession) {
		return getAuthenticatedAs(httpSession).getEmail().equals(Constants.guestEmail);
	}

	/**
	 * Removes authentication data from given <code>session</code>.
	 * @param httpSession
	 */
	public static void logOut(HttpSession httpSession) {
		httpSession.removeAttribute(ATTRIBUTE_SESSION_ACCOUNT);
		log.info("Session '"+ httpSession.getId() +"' has been logged out.");
	}

	/**
	 * Authenticate user as a contest member.
	 * @param contestMember
	 * @param httpSession
	 */
	public static void becomeContestMemberAs(ContestMember contestMember, HttpSession httpSession) {
		httpSession.setAttribute(ATTRIBUTE_SESSION_CONTESTMEMBER, contestMember);
	}

	/**
	 * Returns current user's contest member data.
	 * @param httpSession
	 * @return currentrly auth contestMember or null if there no such one
	 */
	public static ContestMember getContestMemberAs(HttpSession httpSession) {
		return (ContestMember) httpSession.getAttribute(ATTRIBUTE_SESSION_CONTESTMEMBER);
	}
}
