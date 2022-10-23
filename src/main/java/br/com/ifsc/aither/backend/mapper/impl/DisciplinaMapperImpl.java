package br.com.ifsc.aither.backend.mapper.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifsc.aither.backend.domain.Disciplina;
import br.com.ifsc.aither.backend.mapper.DisciplinaMapper;
import br.com.ifsc.aither.backend.model.DisciplinaDTO;

@Component
public class DisciplinaMapperImpl implements DisciplinaMapper {

	@Inject
	private ObjectMapper mapper;

	@Override
	public DisciplinaDTO convertDomainToDTO(Disciplina domain) {
		return mapper.convertValue(domain, DisciplinaDTO.class);
	}

	@Override
	public Disciplina convertDTOToDomain(DisciplinaDTO dto) {
		return mapper.convertValue(dto, Disciplina.class);
	}
}
