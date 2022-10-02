package br.com.ifsc.aither.backend.autocomplete.impl;

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaAutoCompleteImpl implements DisciplinaAutoComplete {

	private Integer id;
	
	private String descricao;
}
