package br.com.ifsc.aither.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.ifsc.aither.backend.dto.UsuarioDTO;

public interface UsuarioService extends UserDetailsService {

	public UsuarioDTO create(UsuarioDTO usuario);
}
