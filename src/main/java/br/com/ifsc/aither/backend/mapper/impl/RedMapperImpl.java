package br.com.ifsc.aither.backend.mapper.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.mapper.RedMapper;
import br.com.ifsc.aither.backend.model.RedDTO;

@Component
public class RedMapperImpl implements RedMapper {

	@Inject
	private ObjectMapper mapper;

	@Override
	public RedDTO convertDomainToDTO(Red domain) {
		return mapper.convertValue(domain, RedDTO.class);
	}

	@Override
	public Red convertDTOToDomain(RedDTO dto) {
		return mapper.convertValue(dto, Red.class);
	}
}
