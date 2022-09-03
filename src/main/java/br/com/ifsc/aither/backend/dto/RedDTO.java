package br.com.ifsc.aither.backend.dto;

import br.com.ifsc.aither.backend.domain.Conteudo;
import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioEntity;
import br.com.ifsc.aither.backend.dto.ConteudoDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedDTO {

	private Integer id;

	@NotBlank(message = "Título é obrigatório")
	private String titulo;

	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;

	@NotBlank(message = "Autor é obrigatório")
	private String autor;

	private byte[] imagem;

	@NotBlank(message = "Endereço é obrigatório")
	private String endereco;

	@NotNull(message = "O campo disciplinas não pode ser nulo")
	private Set<DisciplinaDTO> disciplinas;

	@NotNull(message = "O campo conteúdos não pode ser nulo")
	private Set<ConteudoDTO> conteudos;

	public static RedDTO of(Red entity) {
		var disciplinas = entity.getDisciplinas().stream()
				.map(DisciplinaDTO::of).collect(Collectors.toSet());
		var conteudos = entity.getConteudos().stream()
				.map(ConteudoDTO::of).collect(Collectors.toSet());

		return RedDTO.builder()
				.id(entity.getId())
				.titulo(entity.getTitulo())
				.descricao(entity.getDescricao())
				.autor(entity.getAutor())
				.imagem(entity.getImagem())
				.endereco(entity.getEndereco())
				.disciplinas(disciplinas)
				.conteudos(conteudos)
				.build();
	}
}
