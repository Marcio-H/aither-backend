package br.com.ifsc.aither.backend.domain;

import java.util.Date;
import java.util.Optional;

import org.springframework.core.env.Environment;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

public class Token {

	@Getter
	private final String username;

	private final Environment env;

	private final Long expiration;

	private Token(String username, Environment environment, Long expiration) {
		this.username = username;
		this.env = environment;
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() +  expiration))
				.signWith(SignatureAlgorithm.HS512, Optional.of(env.getProperty("app.token.secret")).get().getBytes())
				.compact();
	}

	public static TokenBuilder builder() {
		return new TokenBuilder();
	}

	//
	public static class TokenBuilder {

		public WithEnvironmentBuilder withEnvironment(Environment environment) {
			return new WithEnvironmentBuilder(environment);
		}

		public class WithEnvironmentBuilder {

			private final Environment environment;

			private WithEnvironmentBuilder(Environment environment) {
				this.environment = environment;
			}

			public FromUsernameBuilder fromUsername(String username) {
				return new FromUsernameBuilder(environment, username);
			}

			public FromStringBuilder fromString(String str) {
				return new FromStringBuilder(environment, str);
			}
		}

		public class FromUsernameBuilder {

			private final Environment environment;
			private final String username;

			private FromUsernameBuilder(Environment environment, String username) {
				this.environment = environment;
				this.username = username;
			}

			public Token buildToken() {
				return token(username, environment);
			}

			public Token buildRefreshToken() {
				return refreshToken(username, environment);
			}
		}

		public class FromStringBuilder {

			private final Environment environment;
			private final String str;

			private FromStringBuilder(Environment environment, String str) {
				this.environment = environment;
				this.str = str;
			}

			public Token buildToken() {
				return token(getUsername(), environment);
			}

			public Token buildRefreshToken() {
				return refreshToken(getUsername(), environment);
			}

			private String getUsername() {
				return Jwts.parser()
						.setSigningKey(Optional.of(environment.getProperty("app.token.secret")).get().getBytes())
						.parseClaimsJws(str)
						.getBody().getSubject();
			}
		}

		private static Token token(String username, Environment environment) {
			var expirationEnv = environment.getProperty("app.token.expiration", Long.class);

			return new Token(username, environment, expirationEnv);
		}

		private static Token refreshToken(String username, Environment environment) {
			var expirationEnv =  environment.getProperty("app.token.refresh.expiration", Long.class);

			return new Token(username, environment, expirationEnv);
		}
	}
}
