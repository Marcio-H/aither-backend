package br.com.ifsc.aither.backend.exceptions;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7147349257003930870L;

	public RecordNotFoundException(String message) {
		super(message);
	}
}
