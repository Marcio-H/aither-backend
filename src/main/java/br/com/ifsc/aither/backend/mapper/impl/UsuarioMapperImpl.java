package br.com.ifsc.aither.backend.mapper.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.mapper.UsuarioMapper;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

	@Inject
	private ObjectMapper mapper;

	@Override
	public Usuario convertAutoCompleteToDomain(UsuarioAutoComplete autoComplete) {
		return mapper.convertValue(autoComplete, Usuario.class);
	}
}
