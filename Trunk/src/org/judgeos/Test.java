package org.judgeos;

public class Test {
	public static void main(String[] args) {
		for (int i = 0x01; i <= 0x80; i++) {
			System.out.println("i="+ Integer.toHexString(i) + (char)i);
		}
	}
}
