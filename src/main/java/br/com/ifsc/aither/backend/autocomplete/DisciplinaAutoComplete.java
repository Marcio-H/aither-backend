package br.com.ifsc.aither.backend.autocomplete;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ifsc.aither.backend.autocomplete.impl.DisciplinaAutoCompleteImpl;

@JsonSerialize(as = DisciplinaAutoCompleteImpl.class)
@JsonDeserialize(as = DisciplinaAutoCompleteImpl.class)
public interface DisciplinaAutoComplete {

	Integer getId();

	String getDescricao();

	void setId(Integer id);

	void setDescricao(String descricao);
}
