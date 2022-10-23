package br.com.ifsc.aither.backend.autocomplete;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ifsc.aither.backend.autocomplete.impl.UsuarioAutoCompleteImpl;

@JsonSerialize(as = UsuarioAutoCompleteImpl.class)
@JsonDeserialize(as = UsuarioAutoCompleteImpl.class)
public interface UsuarioAutoComplete {

	Integer getId();
	String getUsername();
	String getName();
	void setId(Integer id);
	void setUsername(String username);
	void setName(String name);
}
