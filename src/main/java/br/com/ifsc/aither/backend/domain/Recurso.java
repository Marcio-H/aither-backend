package br.com.ifsc.aither.backend.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "tipoRecurso"
)
@JsonSubTypes(
		{
				@JsonSubTypes.Type(value = RecursoBackend.class, name = "B"),
				@JsonSubTypes.Type(value = RecursoFrontend.class, name = "F")
		}
)
public interface Recurso {
}
