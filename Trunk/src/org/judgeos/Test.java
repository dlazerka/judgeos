package org.judgeos;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		Properties p = System.getProperties();
		Enumeration<Object> e = p.elements();
		Set<Object> s = p.keySet();
		for (Object ss : s) {
			System.out.println(ss + " = " + p.get(ss));
		}
	}
}