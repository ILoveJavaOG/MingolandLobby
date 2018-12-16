package de.ilovejava.api;

import java.util.Random;

public class API_RandomString {
	String s;
	public API_RandomString() {
		char[] chars  = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		int length = 12;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		s = sb.toString();
	}
	
	public String getString() {
		return s;
	}
}
