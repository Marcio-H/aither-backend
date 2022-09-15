package br.com.ifsc.aither.backend.domain;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class RedTests {

	@Test
	void builderTest() throws IOException {
		var usuario = Usuario.usuarioNull();
		var imagem = Paths.get("src/test/resources/img/calculator_math_school_tool_icon.png");
		var disciplina = Disciplina.builder()
				.id(1)
				.descricao("DISCIPLINA TESTE")
				.imagem(Files.readAllBytes(imagem))
				.build();
		var conteudo = Conteudo.builder()
				.id(1)
				.descricao("CONTEUDO TESTE")
				.disciplinas(Set.of(disciplina))
				.build();
		var red = Red.builder()
				.id(1)
				.descricao("RED TESTE")
				.titulo("RED TESTE")
				.endereco("http://red.com.br")
				.disciplinas(Set.of(disciplina))
				.conteudos(Set.of(conteudo))
				.criadoPor(usuario)
				.build();

		assertThat(red)
				.extracting(Red::getDisciplinas, as(InstanceOfAssertFactories.collection(Disciplina.class)))
				.isEmpty();
		assertThat(red)
				.extracting(Red::getConteudos, as(InstanceOfAssertFactories.collection(Conteudo.class)))
				.hasSize(1);
	}

	@Test
	void noArgsConstructorTest() throws IOException {
		var usuario = Usuario.usuarioNull();
		var imagem = Paths.get("src/test/resources/img/calculator_math_school_tool_icon.png");
		var disciplina = Disciplina.builder()
				.id(1)
				.descricao("DISCIPLINA TESTE")
				.imagem(Files.readAllBytes(imagem))
				.build();
		var conteudo = Conteudo.builder()
				.id(1)
				.descricao("CONTEUDO TESTE")
				.disciplinas(Set.of(disciplina))
				.build();
		var red = new Red();

		red.setId(1);
		red.setDescricao("RED TESTE");
		red.setEndereco("http://red.com.br");
		red.setTitulo("RED TESTE");
		red.setDisciplinas(Set.of(disciplina));
		red.setConteudos(Set.of(conteudo));
		red.setCriadoPor(usuario);
		assertThat(red)
				.extracting(Red::getDisciplinas, as(InstanceOfAssertFactories.collection(Disciplina.class)))
				.isEmpty();
		assertThat(red)
				.extracting(Red::getConteudos, as(InstanceOfAssertFactories.collection(Conteudo.class)))
				.hasSize(1);

		red = new Red();
		red.setId(1);
		red.setDescricao("RED TESTE");
		red.setEndereco("http://red.com.br");
		red.setTitulo("RED TESTE");
		red.setConteudos(Set.of(conteudo));
		red.setDisciplinas(Set.of(disciplina));
		red.setCriadoPor(usuario);
		assertThat(red)
				.extracting(Red::getDisciplinas, as(InstanceOfAssertFactories.collection(Disciplina.class)))
				.isEmpty();
		assertThat(red)
				.extracting(Red::getConteudos, as(InstanceOfAssertFactories.collection(Conteudo.class)))
				.hasSize(1);
	}
}
