package br.com.ifsc.aither.backend.autocomplete;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ifsc.aither.backend.autocomplete.impl.RedAutoCompleteImpl;

@JsonSerialize(as = RedAutoCompleteImpl.class)
@JsonDeserialize(as = RedAutoCompleteImpl.class)
public interface RedAutoComplete {

	Integer getId();
	String getTitulo();
	void setId(Integer id);
	void setTitulo(String descricao);
}
