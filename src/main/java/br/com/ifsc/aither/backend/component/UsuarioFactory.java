package br.com.ifsc.aither.backend.component;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ifsc.aither.backend.domain.Autorizacao;
import br.com.ifsc.aither.backend.domain.AutorizacaoId;
import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.service.RecursoService;

@Component
public class UsuarioFactory {

	@Autowired
	private RecursoService recursoService;

	public Usuario usuarioNull() {
		return Usuario.builder()
				.id(-1)
				.name("USUÁRIO NULO")
				.username("usuario.nulo@nulo.com")
				.password("SENHA NULA")
				.papel(papelNull())
				.build();
	}

	private Papel papelNull() {
		var recursosPublicos = recursoService.findAllByPermiteTodosIsTrue();
		var autorizacoes = recursosPublicos.stream()
				.map(it -> AutorizacaoId.builder()
						.recurso(it)
						.build())
				.map(it -> Autorizacao.builder()
						.id(it)
						.restrita(true)
						.build())
				.collect(Collectors.toSet());

		return Papel.builder()
				.id(-1)
				.descricao("PAPEL NULL")
				.autorizacaos(autorizacoes)
				.build();
	}
}
