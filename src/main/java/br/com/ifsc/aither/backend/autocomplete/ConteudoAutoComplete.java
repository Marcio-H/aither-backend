package br.com.ifsc.aither.backend.autocomplete;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ifsc.aither.backend.autocomplete.impl.ConteudoAutoCompleteImpl;

@JsonSerialize(as = ConteudoAutoCompleteImpl.class)
@JsonDeserialize(as = ConteudoAutoCompleteImpl.class)
public interface ConteudoAutoComplete {

	Integer getId();

	String getDescricao();

	void setId(Integer id);

	void setDescricao(String descricao);
}
