package br.com.ifsc.aither.backend.component;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.ifsc.aither.backend.domain.Token;

@Component
public class TokenFactory {

	@Inject
	private Environment env;

	public Token generateToken(String username) {
		return Token.builder()
				.withEnvironment(env)
				.fromUsername(username)
				.buildToken();
	}

	public Token tokenOf(String token) {
		return Token.builder()
				.withEnvironment(env)
				.fromString(token)
				.buildToken();
	}
}
