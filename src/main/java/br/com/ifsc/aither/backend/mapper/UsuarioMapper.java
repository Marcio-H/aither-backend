package br.com.ifsc.aither.backend.mapper;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import br.com.ifsc.aither.backend.domain.Usuario;

public interface UsuarioMapper {

	Usuario convertAutoCompleteToDomain(UsuarioAutoComplete autoComplete);
}
