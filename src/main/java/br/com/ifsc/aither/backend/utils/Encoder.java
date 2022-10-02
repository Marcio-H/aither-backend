package br.com.ifsc.aither.backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Encoder {

	private static final BCryptPasswordEncoder INSTANCIA = new BCryptPasswordEncoder();

	public static String encode(String str) {
		return INSTANCIA.encode(str);
	}
}
