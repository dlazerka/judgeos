package org.judgeos.config;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Created by IntelliJ IDEA.
 * User: uzver
 * Date: 13.05.2006
 * Time: 20:22:47
 */

public class Config {
	private static String dbms;
	private static String pgUrl;
	private static String pgUsername;
	private static String pgPassword;
	private static boolean isLoaded = false;

	// TODO fix this dreadful line
	private static String configFilePath = "/usr/webapps/judgeos/WEB-INF/config.txt";


	private static void loadFromFile() throws ConfigReadException {
		FileReader reader;
		try {
			reader = new FileReader(configFilePath);
		} catch (FileNotFoundException e) {
			throw new ConfigReadException("File "+ configFilePath +" not found.");
		}
		BufferedReader breader = new BufferedReader(reader);

		Pattern comment = Pattern.compile("#.*");
		Pattern directive = Pattern.compile("^\\s*([\\w\\.]+)[\\s:=]+(.*)$");
		//String s = "";
		do {
			String str;
			try {
				str = breader.readLine();
			} catch (IOException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					// Do nothing.
				}
				throw new ConfigReadException("Error reading from " + configFilePath);
			}

			if (str == null) {
				break;
			}


			Matcher m = comment.matcher(str);
			str = m.replaceFirst("");
			if (str.length() == 0) {
				continue;
			}

			//s += str;
			m = directive.matcher(str);
			if (m.matches()) {
			//	s = s + " matches";
				String key = m.group(1);
				String value = m.group(2);
				setAttribute(key, value);
			}
			//s += "\n";
		} while (true);

		try {
			reader.close();
		} catch (IOException e) {
			throw new ConfigReadException("Error closing config file");
		}

		setLoaded(true);
	}

	private static void setAttribute(String key, String value) throws ConfigReadException {
		if (key.equals("dbms")) {
			setDbms(value);
		}
		else if (key.equals("pg.url")) {
			setPgUrl(value);
		}
		else if (key.equals("pg.username")) {
			setPgUsername(value);
		}
		else if (key.equals("pg.password")) {
			setPgPassword(value);
		}
		else {
			throw new ConfigReadException("Unknown directive \"" + key);
		}
	}

	public static boolean isLoaded() {
		return isLoaded;
	}

	public static void setLoaded(boolean loaded) {
		isLoaded = loaded;
	}

	public static String getConfigFilePath() {
		return configFilePath;
	}

	public static void setConfigFilePath(String configFilePath) {
		Config.configFilePath = configFilePath;
	}

	private static void setPgUrl(String pgUrl) {
		Config.pgUrl = pgUrl;
	}

	private static void setPgUsername(String pgUsername) {
		Config.pgUsername = pgUsername;
	}

	private static void setPgPassword(String pgPassword) {
		Config.pgPassword = pgPassword;
	}

	private static void setDbms(String dbms) {
		Config.dbms = dbms;
	}

	public static String getDbms() throws ConfigReadException {
		if (!isLoaded) {
			loadFromFile();
		}
		return dbms;
	}

	public static String getPgUrl() throws ConfigReadException {
		if (!isLoaded) {
			loadFromFile();
		}
		return pgUrl;
	}

	public static String getPgUsername() throws ConfigReadException {
		if (!isLoaded) {
			loadFromFile();
		}
		return pgUsername;
	}

	public static String getPgPassword() throws ConfigReadException {
		if (!isLoaded) {
			loadFromFile();
		}
		return pgPassword;
	}
}
