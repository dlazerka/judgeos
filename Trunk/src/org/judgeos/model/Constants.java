package org.judgeos.model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.io.Serializable;

public class Constants implements Serializable {
	public static final Locale defaultLocale = Locale.US;
	public static final TimeZone defaultTimeZone = TimeZone.getTimeZone("Etc/UTC");
	public static final DateFormat defaultDateTimeFormat;
	public static final DecimalFormat defaultNumberFormat = new DecimalFormat("0.##;", new DecimalFormatSymbols(defaultLocale));

	private static final Constants INSTANCE = new Constants();

	static {
		if (defaultTimeZone.hasSameRules(TimeZone.getTimeZone("Etc/UTC"))) {
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		else {
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		}
		defaultDateTimeFormat.setTimeZone(defaultTimeZone);
	}

	/**
	 * Email of the convenience guest account.
	 */
	public static final String guestEmail = "guest@judgeos.org";

	
	/* Getters to make possible use as bean */

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public TimeZone getDefaultTimeZone() {
		return defaultTimeZone;
	}

	public DateFormat getDefaultDateTimeFormat() {
		return defaultDateTimeFormat;
	}

	public DecimalFormat getDefaultNumberFormat() {
		return defaultNumberFormat;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public static Constants getInstantce() {
		return INSTANCE;
	}
}
