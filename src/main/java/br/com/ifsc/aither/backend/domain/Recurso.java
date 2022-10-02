package br.com.ifsc.aither.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Recurso {

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
