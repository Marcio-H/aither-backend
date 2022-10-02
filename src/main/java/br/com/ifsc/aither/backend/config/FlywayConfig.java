package br.com.ifsc.aither.backend.config;

import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ifsc.aither.backend.utils.Encoder;

@Configuration
class FlywayConfig {

	private static final String PASSWORD_KEY = "usuario.admin.password";

	@Bean
	FlywayConfigurationCustomizer flywayConfigurationCustomizer() {
	    return configuration -> {
	    	var placeholders = configuration.getPlaceholders();
	    	var userPassword = placeholders.get(PASSWORD_KEY);

	    	userPassword = Encoder.encode(userPassword);
			placeholders.replace(PASSWORD_KEY, userPassword);
	    };
	}
}
