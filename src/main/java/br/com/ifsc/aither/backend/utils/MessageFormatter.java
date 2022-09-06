package br.com.ifsc.aither.backend.utils;

public class MessageFormatter {

	public static String format(String format, Object... params) {
		return org.slf4j.helpers.MessageFormatter.format(format, params).getMessage();
	}
}
