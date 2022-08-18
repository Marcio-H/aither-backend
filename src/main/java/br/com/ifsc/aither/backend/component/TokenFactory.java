package br.com.ifsc.aither.backend.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.ifsc.aither.backend.domain.Token;

@Component
public class TokenFactory {

	@Autowired
	private Environment env;

	public Token generateToken(String email) {
		return Token.builder()
				.withEnvironment(env)
				.fromEmail(email)
				.buildToken();
	}

	public Token generateRefreshToken(String email) {
		return Token.builder()
				.withEnvironment(env)
				.fromEmail(email)
				.buildRefreshToken();
	}

	public Token tokenOf(String token) {
		return Token.builder()
				.withEnvironment(env)
				.fromString(token)
				.buildToken();
	}

	public Token refreshTokenOf(String token) {
		return Token.builder()
				.withEnvironment(env)
				.fromString(token)
				.buildRefreshToken();
	}
}
