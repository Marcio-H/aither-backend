package br.com.ifsc.aither.backend.domain;

import br.com.ifsc.aither.backend.enums.DominioRecurso;
import br.com.ifsc.aither.backend.enums.TipoRecurso;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Recurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String label;

	@Column
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private DominioRecurso dominio;

	@NotBlank(message = "URN é obrigatório")
	@Column
	private String uri;

	@Builder.Default
	@Column(name = "permite_todos")
	private boolean permiteTodos = false;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private TipoRecurso tipo;
}
