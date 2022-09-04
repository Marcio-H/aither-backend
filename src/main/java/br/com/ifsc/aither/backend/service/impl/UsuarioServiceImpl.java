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
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario create(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository
				.findByUsername(username)
				.orElse(Usuario.usuarioNull());
	}
}
