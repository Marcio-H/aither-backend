package br.com.ifsc.aither.backend.service;

import br.com.ifsc.aither.backend.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

	public Usuario create(Usuario usuario);
}
