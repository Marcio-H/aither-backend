package br.com.ifsc.aither.backend.service.impl;

import br.com.ifsc.aither.backend.domain.Recurso;
import br.com.ifsc.aither.backend.repository.RecursoRepository;
import br.com.ifsc.aither.backend.service.RecursoService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecursoServiceImpl implements RecursoService {

	@Autowired
	private RecursoRepository repository;

	@Override
	public Recurso create(Recurso recurso) {
		return repository.save(recurso);
	}

	@Override
	public List<Recurso> findAllByPermiteTodosIsTrue() {
		return repository.findAllByPermiteTodosIsTrue();
	}

	@Override
	public Optional<Recurso> findByUri(String string) {
		return repository.findByUri(string);
	}
}
