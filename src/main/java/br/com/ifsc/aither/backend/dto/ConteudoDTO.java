package br.com.ifsc.aither.backend.dto;

import br.com.ifsc.aither.backend.domain.Conteudo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoDTO {

	private Integer id;

	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;

	@NotNull(message = "O campo disciplinas não pode ser nulo")
	private Set<DisciplinaDTO> disciplinas;

	public static ConteudoDTO of(Conteudo entity) {
		var disciplinas = entity.getDisciplinas().stream()
				.map(DisciplinaDTO::of)
				.collect(Collectors.toSet());

		return ConteudoDTO.builder()
				.id(entity.getId())
				.descricao(entity.getDescricao())
				.disciplinas(disciplinas)
				.build();
	}
}
