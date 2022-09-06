package br.com.ifsc.aither.backend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recurso_frontend")
public class RecursoFrontend implements Recurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column
	private String descricao;
}
