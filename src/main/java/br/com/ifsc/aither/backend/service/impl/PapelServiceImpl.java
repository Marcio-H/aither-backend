package br.com.ifsc.aither.backend.service.impl;

import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.repository.PapelRepository;
import br.com.ifsc.aither.backend.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PapelServiceImpl implements PapelService {

	@Autowired
	private PapelRepository repository;

	@Transactional
	@Override
	public Papel create(Papel papel) {
		return repository.save(papel);
	}
}
