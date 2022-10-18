package br.com.ifsc.aither.backend.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL) 
public class ConteudoDTO {

	private Integer id;

	@NotBlank(message = "descricao é obrigatório.")
	@Size(max = 255, message = "descricao pode ter no máximo 255 caracteres.")
	private String descricao;

	@NotEmpty(message = "Disciplinas é obrigatório")
	private Set<DisciplinaAutoComplete> disciplinas;
}
