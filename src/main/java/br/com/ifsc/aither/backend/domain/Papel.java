package br.com.ifsc.aither.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Papel {

	public static final String ADMINISTRADOR = "ADMINISTRADOR";
	public static final String PROFESSOR = "PROFESSOR";
	public static final String ESTUDANTE = "ESTUDANTE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column
	private String descricao;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = EAGER)
	@JoinColumn(name = "papel_id")
	private Set<Autorizacao> autorizacaos = Set.of();

	public boolean possuiAcessoPara(String uri) {
		return autorizacaos.stream().anyMatch(it -> it.possuiAcessoPara(uri));
	}
}
