package br.com.ifsc.aither.backend.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Autorizacao {

	@EmbeddedId
	private AutorizacaoId id;

	@Column
	private boolean restrita;

	public boolean possuiAcessoPara(String uri) {
		return id.getRecurso().getUri().equals(uri);
	}
}
