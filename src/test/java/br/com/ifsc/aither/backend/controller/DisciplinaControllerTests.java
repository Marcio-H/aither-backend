package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.domain.Disciplina;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(BaseTests.TestConfig.class)
class DisciplinaControllerTests extends BaseTests {

	@Autowired
	private DisciplinaController controller;

	@Test
	void criarTest() throws IOException {
		var image = Paths.get("src/test/resources/img/calculator_math_school_tool_icon.png");
		var dto = Disciplina.builder()
				.descricao("DISCIPLINA TESTE")
				.imagem(Files.readAllBytes(image))
				.build();
		var criado = controller.create(dto);

		assertThat(criado).isNotNull()
				.extracting("id", "descricao")
				.isNotNull()
				.contains(dto.getDescricao(), Index.atIndex(1));
	}
}
