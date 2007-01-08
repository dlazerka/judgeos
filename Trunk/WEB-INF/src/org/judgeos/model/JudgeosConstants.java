package org.judgeos.model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class JudgeosConstants {
	public static final Locale defaultLocale = Locale.US;
	public static final TimeZone defaultTimeZone = TimeZone.getTimeZone("Etc/GMT");
	public static final DateFormat defaultDateTimeFormat;
	public static final DecimalFormat defaultNumberFormat = new DecimalFormat("0.##;", new DecimalFormatSymbols(defaultLocale));

	static {
		if (defaultTimeZone.hasSameRules(TimeZone.getTimeZone("Etc/UTC"))) {
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		else {
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		}
		defaultDateTimeFormat.setTimeZone(defaultTimeZone);
	}

	public static final String guestCodename = "guest";
}
