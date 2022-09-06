package br.com.ifsc.aither.backend.service.impl;

import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.repository.UsuarioRepository;
import br.com.ifsc.aither.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario create(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository
				.findByUsername(username)
				.orElse(Usuario.usuarioNull());
	}
}
