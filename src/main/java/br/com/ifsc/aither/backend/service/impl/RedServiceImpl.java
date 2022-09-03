package br.com.ifsc.aither.backend.service.impl;

import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioEntity;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioNull;
import br.com.ifsc.aither.backend.dto.RedDTO;
import br.com.ifsc.aither.backend.repository.RedRepository;
import br.com.ifsc.aither.backend.service.RedService;
import br.com.ifsc.aither.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RedServiceImpl implements RedService {
	@Autowired
	private RedRepository repository;

	@Autowired
	private UsuarioService usuarioService;


	@Override
	public RedDTO create(RedDTO dto) {
		var usuario= usuarioService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		var criado = repository.save(Red.of(dto, (UsuarioEntity) usuario));

		return RedDTO.of(criado);
	}
}
