package br.com.ifsc.aither.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import br.com.ifsc.aither.backend.domain.Disciplina;

@SpringJUnitConfig(BaseTests.TestConfig.class)
class DisciplinaControllerTests extends BaseTests {

	@Inject
	private DisciplinaController controller;

	@Test
	void criarTest() throws IOException {
//		var image = Paths.get("src/test/resources/img/calculator_math_school_tool_icon.png");
//		var dto = Disciplina.builder()
//				.descricao("DISCIPLINA TESTE")
//				.imagem(Files.readAllBytes(image))
//				.build();
//		var criado = controller.create(dto);
//
//		assertThat(criado).isNotNull()
//				.extracting("id", "descricao")
//				.isNotNull()
//				.contains(dto.getDescricao(), Index.atIndex(1));
	}
}
