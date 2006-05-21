package org.judgeos.config;

import org.judgeos.IncorrectSetupException;

/**
 * Created by IntelliJ IDEA.
 * User: uzver
 * Date: 13.05.2006
 * Time: 20:53:26
 */
public class ConfigReadException extends IncorrectSetupException {
	ConfigReadException(String message) {
		super(message);
	}

	ConfigReadException(String message, Throwable cause) {
		super(message, cause);
	}
}
