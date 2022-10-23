package br.com.ifsc.aither.backend.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.exceptions.RecordNotFoundException;
import br.com.ifsc.aither.backend.mapper.RedMapper;
import br.com.ifsc.aither.backend.model.RedDTO;
import br.com.ifsc.aither.backend.repository.RedRepository;
import br.com.ifsc.aither.backend.service.RedService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class RedServiceImpl implements RedService {

	@Inject
	private RedRepository repository;

	@Inject
	private RedMapper mapper;

	@Inject
	private UsuarioService usuarioService;

	@Transactional
	@Override
	public RedDTO create(RedDTO redDTO) {
		var domain = mapper.convertDTOToDomain(redDTO);
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var usuario = usuarioService.loadUserByUsername(username);

		domain.setCriadoPor(usuario);

		var created = repository.save(domain);

		return mapper.convertDomainToDTO(created);
	}

	@Transactional
	@Override
	public RedDTO read(Integer id) {
		var domain = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Red não encontrado"));

		return mapper.convertDomainToDTO(domain);
	}

	@Transactional
	@Override
	public RedDTO update(RedDTO redDTO) {
		var domain = mapper.convertDTOToDomain(redDTO);
		var redSalvo = read(domain.getId());
		var usuarioOriginal = usuarioService.convertAutoCompleteToDomain(redSalvo.getCriadoPor());

		domain.setCriadoPor(usuarioOriginal);

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
	public Page<RedDTO> listAsDTO(String query, Pageable pageable) {
		var page = repository.findAll(query, pageable);

		return page.map(mapper::convertDomainToDTO);
	}

	@Transactional
	@Override
	public Page<RedAutoComplete> autoComplete(String query, Pageable pageable) {
		return repository.autoComplete(query, pageable);
	}
}
