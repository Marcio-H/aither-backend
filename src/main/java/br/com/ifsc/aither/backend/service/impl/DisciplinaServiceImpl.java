package br.com.ifsc.aither.backend.service.impl;

import br.com.ifsc.aither.backend.domain.Disciplina;
import br.com.ifsc.aither.backend.dto.DisciplinaDTO;
import br.com.ifsc.aither.backend.repository.DisciplinaRepository;
import br.com.ifsc.aither.backend.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;

	@Override
	public DisciplinaDTO create(DisciplinaDTO dto) {
		var criado = repository.save(Disciplina.of(dto));

		return DisciplinaDTO.of(criado);
	}
}
