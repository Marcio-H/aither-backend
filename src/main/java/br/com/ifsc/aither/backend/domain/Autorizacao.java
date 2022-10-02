package br.com.ifsc.aither.backend.domain;

import static com.vladmihalcea.hibernate.type.array.internal.AbstractArrayType.SQL_ARRAY_TYPE;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifsc.aither.backend.enums.DominioRecurso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(AutorizacaoId.class)
public class Autorizacao {

	@JsonIgnore
	@ToString.Exclude
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "papel_id", nullable = false)
	private Papel papel;

	@Id
	@ManyToOne
	@JoinColumn(name = "recurso_id")
	private Recurso recurso;

	@Column
	private boolean restrita;

	@Builder.Default
	@Enumerated
	@Type(type = "list-array", parameters = { @Parameter(name = SQL_ARRAY_TYPE, value = "TEXT") })
	@Column
	private List<DominioRecurso> dominios = new ArrayList<>();

	public boolean possuiAcessoPara(String urn, DominioRecurso domain) {
		if (recurso.getUrn().equals(urn)) {
			return dominios.contains(domain);
		}
		return false;
	}
}
