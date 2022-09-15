package br.com.ifsc.aither.backend.domain;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacaoId implements Serializable {

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "papel_id")
	private Papel papel;

	@ManyToOne
	@JoinColumn( name = "recurso_id")
	private Recurso recurso;
}
