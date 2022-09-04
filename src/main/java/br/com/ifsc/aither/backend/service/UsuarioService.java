package br.com.ifsc.aither.backend.service;

import br.com.ifsc.aither.backend.domain.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioService extends UserDetailsService {

	Usuario create(Usuario usuario);

	@Override
	Usuario loadUserByUsername(String username) throws UsernameNotFoundException;
}
