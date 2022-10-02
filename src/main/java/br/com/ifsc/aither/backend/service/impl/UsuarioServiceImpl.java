package br.com.ifsc.aither.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.repository.UsuarioRepository;
import br.com.ifsc.aither.backend.service.PapelService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PapelService papelService;

	@Override
	public Usuario create(Usuario usuario) {
		var papelEstudante = papelService.findPapelByDescricao(Papel.ESTUDANTE);

		usuario.setPapel(papelEstudante.get());
		return repository.save(usuario);
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository
				.findByUsername(username)
				.orElse(Usuario.usuarioNull());
	}
}
