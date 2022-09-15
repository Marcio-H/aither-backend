package br.com.ifsc.aither.backend.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(SpringContext.NAME)
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Lazy(value = false)
public class SpringContext {

	public static final String NAME = "springContext";

	private static ApplicationContext applicationContext;
	private static Environment environment;

	@Inject
	public SpringContext(ApplicationContext applicationContext, Environment environment) {
		SpringContext.applicationContext = applicationContext;
		SpringContext.environment = environment;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Environment getEnvironment() {
		return environment;
	}
}
