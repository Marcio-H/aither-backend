package br.com.ifsc.aither.backend.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL) 
public class DisciplinaDTO {

	private Integer id;

	@NotBlank(message="descricao é obrigatório.")
	@Size(max = 255, message = "descricao pode ter no máximo 255 caracteres.")
	private String descricao;

	@NotNull(message="imagem é obrigatório.")
	private byte[] imagem;
}
