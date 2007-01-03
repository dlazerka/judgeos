package org.judgeos;

import java.text.*;
import java.util.Locale;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		Locale l = Locale.US;
		TimeZone tz = TimeZone.getTimeZone("Etc/UTC");

		GregorianCalendar c = new GregorianCalendar(tz, l);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", l);
		DecimalFormat nf = new DecimalFormat("0.##;", new DecimalFormatSymbols(l));

//		NumberFormat nf = NumberFormat.getInstance(l);
//		DateFormat df = new SimpleDateFormat("Y", l);
		Date d = c.getTime();
		System.out.println(df.format(d));
		System.out.println(nf.format(0.456));
		System.out.println(nf.format(12243.456));
		System.out.println(nf.format(0));
		System.out.println(nf.format(1));
		System.out.println(nf.format(12243));

/*
   // Print out a number using the localized number, integer, currency,
   // and percent format for each locale
   Locale[] locales = NumberFormat.getAvailableLocales();
   double myNumber = -1234.56;
   NumberFormat form;
   for (int j=0; j <= 4; ++j) {
       System.out.println("FORMAT");
       for (int i = 0; i < locales.length; ++i) {
           if (locales[i].getCountry().length() == 0) {
              continue; // Skip language-only locales
           }
           System.out.print(locales[i].getDisplayName());
           switch (j) {
           case 0:
               form = NumberFormat.getInstance(locales[i]); break;
           case 1:
               form = NumberFormat.getIntegerInstance(locales[i]); break;
           case 2:
               form = NumberFormat.getCurrencyInstance(locales[i]); break;
           default:
               form = NumberFormat.getPercentInstance(locales[i]); break;
           }
           if (form instanceof DecimalFormat) {
               System.out.print(": " + ((DecimalFormat) form).toPattern());
           }
           System.out.print(" -> " + form.format(myNumber));
           try {
               System.out.println(" -> " + form.parse(form.format(myNumber)));
           } catch (ParseException e) {}
       }
   }
   */
	}
}