package br.com.ifsc.aither.backend.component;

import br.com.ifsc.aither.backend.domain.Autorizacao;
import br.com.ifsc.aither.backend.domain.AutorizacaoId;
import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsuarioFactory {

	@Autowired
	private RecursoRepository recursoRepository;

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
		var recursosPublicos = recursoRepository.findAllByPermiteTodosIsTrue();
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
