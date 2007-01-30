package org.judgeos.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Constants implements Serializable {
	private static final Constants INSTANCE = new Constants();

	public static final Locale defaultLocale = Locale.US;
	public static final TimeZone defaultTimeZone = TimeZone.getTimeZone("Etc/UTC");
	public static final DateFormat defaultDateTimeFormat;

	static {
		if (defaultTimeZone.hasSameRules(TimeZone.getTimeZone("Etc/UTC"))) {
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		}
		defaultDateTimeFormat.setTimeZone(defaultTimeZone);
	}

	public static final DecimalFormat defaultNumberFormat = new DecimalFormat(
		"0.##;", new DecimalFormatSymbols(defaultLocale)
	);

	public static final Pattern passwordMask = Pattern.compile("^[\\x21-\\x7E]*$");
	public static final Pattern emailAddressMask;

	static {
		String atom = "[0-9a-zA-Z!#$%&+/=?^_{|}~-]+";
		String dotAtom = "(" + atom + "(\\." + atom + ")*" + ")";
		String regexp = "^" + dotAtom + "@" + dotAtom + "$";
		emailAddressMask = Pattern.compile(regexp);
	}

	public static final Pattern fullNameMask = Pattern.compile("^[\\x20-\\x7E]*$");


	/**
	 * Email of the convenience guest account.
	 */
	public static final String guestEmail = "guest@judgeos.org";

	/**
	 * To make possible use as bean.
	 *
	 * @return singleton instance
	 */
	public static Constants getInstantce() {
		return INSTANCE;
	}

	/*
	 *  Getters to make possible use as bean
	 */


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

	public Pattern getPasswordMask() {
		return passwordMask;
	}
}
