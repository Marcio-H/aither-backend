package br.com.ifsc.aither.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.repository.RedRepository;
import br.com.ifsc.aither.backend.service.RedService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class RedServiceImpl implements RedService {

	@Autowired
	private RedRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Red create(Red red) {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var usuario= usuarioService.loadUserByUsername(username);

		red.setCriadoPor(usuario);
		return repository.save(red);
	}
}
