package br.com.ifsc.aither.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.enums.DominioRecurso;
import br.com.ifsc.aither.backend.model.Permissao;

public interface UsuarioService extends UserDetailsService {

	Usuario create(Usuario usuario);
	Usuario convertAutoCompleteToDomain(UsuarioAutoComplete autoComplete);

	@Override
	Usuario loadUserByUsername(String username) throws UsernameNotFoundException;
	List<Permissao> getPermissions();
	Boolean hasPermission(String urn, DominioRecurso recurso);
}
