package br.com.ifsc.aither.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.enums.DominioRecurso;
import br.com.ifsc.aither.backend.mapper.UsuarioMapper;
import br.com.ifsc.aither.backend.model.Permissao;
import br.com.ifsc.aither.backend.repository.UsuarioRepository;
import br.com.ifsc.aither.backend.service.PapelService;
import br.com.ifsc.aither.backend.service.SecurityService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	private UsuarioRepository repository;

	@Inject
	private UsuarioMapper mapper;

	@Inject
	private PapelService service;
//
//	@Inject
//	private SecurityService securityService;

	@Transactional
	@Override
	public Usuario create(Usuario usuario) {
		var papelEstudante = service.findPapelByDescricao(Papel.ESTUDANTE);

		usuario.setPapel(papelEstudante.get());
		return repository.save(usuario);
	}

	@Transactional
	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository
				.findByUsername(username)
				.orElse(Usuario.usuarioNull());
	}

	@Transactional
	@Override
	public Usuario convertAutoCompleteToDomain(UsuarioAutoComplete autoComplete) {
		return mapper.convertAutoCompleteToDomain(autoComplete);
	}

	@Transactional
	@Override
	public List<Permissao> getPermissions() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var usuario = loadUserByUsername(username);

		return usuario.getPapel().getAutorizacaos().stream()
			.map(autorizacao -> {
				return Permissao.builder()
						.urn(autorizacao.getRecurso().getUrn())
						.build();
			})
			.collect(Collectors.toList());
	}

	@Override
	public Boolean hasPermission(String urn, DominioRecurso recurso) {
		return null;
	}
}
