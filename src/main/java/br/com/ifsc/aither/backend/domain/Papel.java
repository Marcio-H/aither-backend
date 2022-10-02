package br.com.ifsc.aither.backend.domain;

import static java.util.Objects.isNull;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ifsc.aither.backend.enums.DominioRecurso;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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

	@NotNull
	@OneToMany(mappedBy = "papel", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Autorizacao> autorizacaos = new HashSet<>();

	@Builder
	public Papel(Integer id, String descricao, Set<Autorizacao> autorizacaos) {
		this.id = id;
		this.descricao = descricao;
		if (isNull(autorizacaos)) {
			autorizacaos = Set.of();
		}
		autorizacaos.forEach(this::adicionarAutorizacao);
	}

	public static Papel papelNull() {
		return Papel.builder()
				.descricao("PAPEL NULL")
				.build();
	}

	public void adicionarAutorizacao(Autorizacao autorizacao) {
		autorizacao.setPapel(this);
		this.autorizacaos.add(autorizacao);
	}

	public boolean possuiAcessoPara(String urn, DominioRecurso domain) {
		return autorizacaos.stream().anyMatch(it -> it.possuiAcessoPara(urn, domain));
	}
}
