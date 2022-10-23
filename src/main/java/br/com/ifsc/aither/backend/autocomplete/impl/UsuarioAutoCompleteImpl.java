package br.com.ifsc.aither.backend.autocomplete.impl;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAutoCompleteImpl implements UsuarioAutoComplete {

	private Integer id;
	private String username;
	private String name;
}
