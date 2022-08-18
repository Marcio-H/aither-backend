package br.com.ifsc.aither.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.domain.usuario.Usuario;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioEntity;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioNull;
import br.com.ifsc.aither.backend.dto.UsuarioDTO;
import br.com.ifsc.aither.backend.model.repository.UsuarioRepository;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UsuarioDTO create(UsuarioDTO usuario) {
		usuario.setSenha(bCryptPasswordEncoder
				.encode(usuario.getSenha()));
		usuarioRepository.save(new UsuarioEntity(usuario));
		return usuario;
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository
				.findByEmail(username)
				.orElse(UsuarioNull.builder().build());
	}
}
