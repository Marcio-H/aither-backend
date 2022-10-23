package br.com.ifsc.aither.backend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recurso implements Serializable {

	private static final long serialVersionUID = 494386011978556272L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "URN é obrigatório")
	@Column
	private String urn;

	public static Recurso recursoNull() {
		return Recurso.builder()
				.id(-1)
				.urn("/null")
				.build();
	}
}
