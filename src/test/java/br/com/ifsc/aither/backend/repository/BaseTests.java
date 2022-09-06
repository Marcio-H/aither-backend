package br.com.ifsc.aither.backend.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest(showSql = false)
@TestPropertySource(properties = "spring.flyway.locations=classpath:/db/migration/base_test/")
class BaseTests {

	@TestConfiguration
	static class TestConfig {

	}
}
