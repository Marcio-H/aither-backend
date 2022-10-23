package br.com.ifsc.aither.backend.autocomplete.impl;

import java.util.Set;

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoAutoCompleteImpl implements ConteudoAutoComplete {

	private Integer id;

	private String descricao;

	private Set<DisciplinaAutoComplete> disciplinas;
}
