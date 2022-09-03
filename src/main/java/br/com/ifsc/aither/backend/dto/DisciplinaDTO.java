package br.com.ifsc.aither.backend.dto;

import br.com.ifsc.aither.backend.domain.Disciplina;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {

	private Integer id;

	private String descricao;

	private byte[] imagem;

	public static DisciplinaDTO of(Disciplina entity) {
		return DisciplinaDTO.builder()
				.id(entity.getId())
				.descricao(entity.getDescricao())
				.imagem(entity.getImagem())
				.build();
	}
}
