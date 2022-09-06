package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.domain.Recurso;
import br.com.ifsc.aither.backend.domain.RecursoBackend;
import br.com.ifsc.aither.backend.domain.RecursoFrontend;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Set;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(BaseTests.TestConfig.class)
class PapelTests extends BaseTests {

	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private RecursoBackendRepository recursoBackendRepository;

	@Autowired
	private RecursoFrontendRepository recursoFrontendRepository;

	@Test
	void createTest() {
		RecursoBackend backend = RecursoBackend.builder()
				.descricao("Recurso back")
				.build();
		RecursoFrontend front = RecursoFrontend.builder()
				.descricao("Recruso front")
				.build();

		backend = recursoBackendRepository.save(backend);
		front = recursoFrontendRepository.save(front);

		Papel papel = Papel.builder()
				.descricao("Papel")
				.recursos(Set.of(backend, front))
				.build();

		papelRepository.save(papel);
	}

	@Test
	@Sql({ "classpath:db/sql/papeis_e_recursos_data.sql" })
	void readTest() {
		var papel = papelRepository.findById(1);

		assertThat(papel).isNotNull()
				.get()
				.extracting(Papel::getRecursos, as(InstanceOfAssertFactories.collection(Recurso.class)))
				.hasSize(2);
	}
}
