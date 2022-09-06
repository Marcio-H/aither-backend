package br.com.ifsc.aither.backend.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.DETACH;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Papel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column
	private String descricao;

	@ManyToAny(
			metaDef = "RecursoMetaDef",
			metaColumn = @Column(name = "tipo_recurso")
	)
	@Cascade({ DETACH })
	@JoinTable(
			name = "papel_recurso",
			joinColumns = @JoinColumn(name = "papel_id"),
			inverseJoinColumns = @JoinColumn(name = "recurso_id")
	)
	private Set<Recurso> recursos = Set.of();
}
