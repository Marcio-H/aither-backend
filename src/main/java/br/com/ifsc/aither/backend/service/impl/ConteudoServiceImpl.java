package br.com.ifsc.aither.backend.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.exceptions.RecordNotFoundException;
import br.com.ifsc.aither.backend.mapper.ConteudoMapper;
import br.com.ifsc.aither.backend.model.ConteudoDTO;
import br.com.ifsc.aither.backend.repository.ConteudoRepository;
import br.com.ifsc.aither.backend.service.ConteudoService;

@Service
public class ConteudoServiceImpl implements ConteudoService {

	@Inject
	private ConteudoRepository repository;

	@Inject
	private ConteudoMapper mapper;

	@Transactional
	@Override
	public ConteudoDTO create(ConteudoDTO conteudoDTO) {
		var domain = mapper.convertDTOToDomain(conteudoDTO);
		var created = repository.save(domain);

		return mapper.convertDomainToDTO(created);
	}

	@Transactional
	@Override
	public ConteudoDTO read(Integer id) {
		var domain = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Conteúdo não encontrado"));

		return mapper.convertDomainToDTO(domain);
	}

	@Transactional
	@Override
	public ConteudoDTO update(ConteudoDTO conteudoDTO) {
		var domain = mapper.convertDTOToDomain(conteudoDTO);
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
	public Page<ConteudoDTO> listAsDTO(String query, Pageable pageable) {
		var page = repository.findAll(query, pageable);

		return page.map(mapper::convertDomainToDTO);
	}

	@Transactional
	@Override
	public Page<ConteudoAutoComplete> autoComplete(String query, Pageable pageable) {
		return repository.autoComplete(query, pageable);
	}
}
