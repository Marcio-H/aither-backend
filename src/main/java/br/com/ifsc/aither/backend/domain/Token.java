package br.com.ifsc.aither.backend.domain;

import java.util.Date;
import java.util.Optional;

import org.springframework.core.env.Environment;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

public class Token {

	@Getter
	private final String email;

	private final Environment env;

	private final Long expiration;

	private Token(String email, Environment environment, Long expiration) {
		this.email = email;
		this.env = environment;
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return Jwts.builder()
				.setSubject(email)
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

			public FromEmailBuilder fromEmail(String email) {
				return new FromEmailBuilder(environment, email);
			}

			public FromStringBuilder fromString(String str) {
				return new FromStringBuilder(environment, str);
			}
		}

		public class FromEmailBuilder {

			private final Environment environment;
			private final String email;

			private FromEmailBuilder(Environment environment, String email) {
				this.environment = environment;
				this.email = email;
			}

			public Token buildToken() {
				return token(email, environment);
			}

			public Token buildRefreshToken() {
				return refreshToken(email, environment);
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
				return token(getEmail(), environment);
			}

			public Token buildRefreshToken() {
				return refreshToken(getEmail(), environment);
			}

			private String getEmail() {
				return Jwts.parser()
						.setSigningKey(Optional.of(environment.getProperty("app.token.secret")).get().getBytes())
						.parseClaimsJws(str)
						.getBody().getSubject();
			}
		}

		private static Token token(String email, Environment environment) {
			var expirationEnv = environment.getProperty("app.token.expiration", Long.class);

			return new Token(email, environment, expirationEnv);
		}

		private static Token refreshToken(String email, Environment environment) {
			var expirationEnv =  environment.getProperty("app.token.refresh.expiration", Long.class);

			return new Token(email, environment, expirationEnv);
		}

	}
}
