package br.com.ifsc.aither.backend.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacaoId implements Serializable {

	private static final long serialVersionUID = -6574000723482895847L;

	private Papel papel;
	private Recurso recurso;
}
