package br.com.ifsc.aither.backend.service.impl;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.mapper.UsuarioMapper;
import br.com.ifsc.aither.backend.repository.UsuarioRepository;
import br.com.ifsc.aither.backend.service.PapelService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	private UsuarioRepository repository;

	@Inject
	private UsuarioMapper mapper;

	@Inject
	private PapelService service;

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
}
