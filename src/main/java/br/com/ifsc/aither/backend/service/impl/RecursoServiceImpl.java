package br.com.ifsc.aither.backend.service.impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.domain.Recurso;
import br.com.ifsc.aither.backend.repository.RecursoRepository;
import br.com.ifsc.aither.backend.service.RecursoService;

@Service
public class RecursoServiceImpl implements RecursoService {

	@Inject
	private RecursoRepository repository;

	@Override
	public Recurso create(Recurso recurso) {
		return repository.save(recurso);
	}
}
