package br.com.ifsc.aither.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/create")
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioService.create(usuario);
	}
}
