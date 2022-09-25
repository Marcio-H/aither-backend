package br.com.ifsc.aither.backend.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsc.aither.backend.domain.Recurso;
import br.com.ifsc.aither.backend.enums.TipoRecurso;
import br.com.ifsc.aither.backend.service.UsuarioService;

@RestController
@RequestMapping("/recurso")
public class RecursoController {

	@Inject
	private UsuarioService usuarioService;

	@GetMapping(value = "/frontend/disponivel")
	public Set<Recurso> getRecursosDisponiveis() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();

		return usuarioService.loadUserByUsername(username)
				.getPapel().getAutorizacaos().stream()
				.map(it -> it.getId().getRecurso())
				.filter(it -> it.getTipo().equals(TipoRecurso.FRONTEND))
				.collect(Collectors.toSet());
	}
}
