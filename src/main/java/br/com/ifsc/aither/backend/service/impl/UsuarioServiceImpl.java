package br.com.ifsc.aither.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.domain.usuario.Usuario;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioEntity;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioNull;
import br.com.ifsc.aither.backend.repository.UsuarioRepository;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario create(Usuario usuario) {
		return usuarioRepository.save(UsuarioEntity.builder()
				.email(usuario.getUsername())
				.nome(usuario.getNome())
				.senha(bCryptPasswordEncoder.encode(usuario.getPassword()))
				.build());
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository
				.findByEmail(username)
				.orElse(new UsuarioNull());
	}
}
