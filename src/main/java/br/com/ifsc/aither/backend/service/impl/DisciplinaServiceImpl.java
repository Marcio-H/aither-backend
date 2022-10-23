package br.com.ifsc.aither.backend.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.exceptions.RecordNotFoundException;
import br.com.ifsc.aither.backend.mapper.DisciplinaMapper;
import br.com.ifsc.aither.backend.model.DisciplinaDTO;
import br.com.ifsc.aither.backend.repository.DisciplinaRepository;
import br.com.ifsc.aither.backend.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

	@Inject
	private DisciplinaRepository repository;

	@Inject
	private DisciplinaMapper mapper;

	@Transactional
	@Override
	public DisciplinaDTO create(DisciplinaDTO disciplinaDTO) {
		var domain = mapper.convertDTOToDomain(disciplinaDTO);
		var created = repository.save(domain);

		return mapper.convertDomainToDTO(created);
	}

	@Transactional
	@Override
	public DisciplinaDTO read(Integer id) {
		var domain = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Disciplina não encontrada"));

		return mapper.convertDomainToDTO(domain);
	}

	@Transactional
	@Override
	public DisciplinaDTO update(DisciplinaDTO disciplinaDTO) {
		var domain = mapper.convertDTOToDomain(disciplinaDTO);
		var updated = repository.save(domain);

		return mapper.convertDomainToDTO(updated);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public Page<DisciplinaDTO> listAsDTO(String query, Pageable pageable) {
		var page = repository.findAll(query, pageable);

		return page.map(mapper::convertDomainToDTO);
	}

	@Transactional
	@Override
	public Page<DisciplinaAutoComplete> autoComplete(String query, Pageable pageable) {
		return repository.autoComplete(query, pageable);
	}
}
