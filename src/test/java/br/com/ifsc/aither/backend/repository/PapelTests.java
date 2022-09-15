package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.*;
import br.com.ifsc.aither.backend.enums.TipoRecurso;
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
	private RecursoRepository recursoRepository;

	@Test
	void createTest() {
		Recurso recurso = Recurso.builder()
				.urn("/red/create")
				.tipo(TipoRecurso.BACKEND)
				.build();

		recurso = recursoRepository.save(recurso);

		AutorizacaoId autorizacaoId= AutorizacaoId.builder().recurso(recurso).build();
		Autorizacao autorizacao = Autorizacao.builder()
				.id(autorizacaoId)
				.build();
		Papel papel = Papel.builder()
				.descricao("Papel")
				.autorizacaos(Set.of(autorizacao))
				.build();

		papelRepository.save(papel);
	}

	@Test
	@Sql({ "classpath:db/sql/papeis_e_recursos_data.sql" })
	void readTest() {
		var papel = papelRepository.findById(1);

		assertThat(papel).isNotNull()
				.get()
				.extracting(Papel::getAutorizacaos, as(InstanceOfAssertFactories.collection(Autorizacao.class)))
				.hasSize(2);
	}
}
