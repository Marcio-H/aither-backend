package br.com.ifsc.aither.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import br.com.ifsc.aither.backend.domain.Usuario;

public interface UsuarioService extends UserDetailsService {

	Usuario create(Usuario usuario);
	Usuario convertAutoCompleteToDomain(UsuarioAutoComplete autoComplete);

	@Override
	Usuario loadUserByUsername(String username) throws UsernameNotFoundException;
}
