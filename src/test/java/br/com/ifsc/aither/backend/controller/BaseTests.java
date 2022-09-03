package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.service.DisciplinaService;
import br.com.ifsc.aither.backend.service.impl.DisciplinaServiceImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest(showSql = false)
@TestPropertySource(properties = "spring.flyway.locations=classpath:/db/migration/base_test/")
class BaseTests {

	@TestConfiguration
	static class TestConfig {

		@Bean
		DisciplinaController disciplinaController() {
			return new DisciplinaController();
		}

		@Bean
		DisciplinaService disciplinaService() {
			return new DisciplinaServiceImpl();
		}
	}
}
