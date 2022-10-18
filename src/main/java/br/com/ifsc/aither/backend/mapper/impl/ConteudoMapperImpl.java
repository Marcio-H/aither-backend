package br.com.ifsc.aither.backend.mapper.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifsc.aither.backend.domain.Conteudo;
import br.com.ifsc.aither.backend.mapper.ConteudoMapper;
import br.com.ifsc.aither.backend.model.ConteudoDTO;

@Component
public class ConteudoMapperImpl implements ConteudoMapper {

	@Inject
	private ObjectMapper mapper;

	@Override
	public ConteudoDTO convertDomainToDto(Conteudo domain) {
		return mapper.convertValue(domain, ConteudoDTO.class);
	}

	@Override
	public Conteudo convertDtoToDomain(ConteudoDTO dto) {
		return mapper.convertValue(dto, Conteudo.class);
	}
}
