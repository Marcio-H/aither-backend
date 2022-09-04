package br.com.ifsc.aither.backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public static String encode(String str) {
		return encoder.encode(str);
	}
}
