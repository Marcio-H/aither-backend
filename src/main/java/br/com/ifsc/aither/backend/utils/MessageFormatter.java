package br.com.ifsc.aither.backend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageFormatter {

	public static String format(String format, Object... params) {
		return org.slf4j.helpers.MessageFormatter.arrayFormat(format, params).getMessage();
	}
}
