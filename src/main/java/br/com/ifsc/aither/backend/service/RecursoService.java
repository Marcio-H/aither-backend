package br.com.ifsc.aither.backend.service;

import java.util.List;
import java.util.Optional;

import br.com.ifsc.aither.backend.domain.Recurso;

public interface RecursoService {

	Recurso create(Recurso recurso);
	List<Recurso> findAllByPermiteTodosIsTrue();
	Optional<Recurso> findByUri(String string);
}
