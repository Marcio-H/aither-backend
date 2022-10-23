package br.com.ifsc.aither.backend.autocomplete.impl;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedAutoCompleteImpl implements RedAutoComplete {

	private Integer id;
	private String titulo;
}
