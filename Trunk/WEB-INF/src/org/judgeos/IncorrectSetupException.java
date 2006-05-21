package org.judgeos;

/**
 * Created by IntelliJ IDEA.
 * User: uzver
 * Date: 13.05.2006
 * Time: 21:46:43
 */
public class IncorrectSetupException extends Exception {
	public IncorrectSetupException(String message) {
		super(message);
	}

	public IncorrectSetupException(String message, Throwable cause) {
		super(message + "(" + cause + ")", cause);
	}
}
