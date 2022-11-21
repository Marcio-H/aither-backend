package br.com.ifsc.aither.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsc.aither.backend.domain.Usuario;
import br.com.ifsc.aither.backend.enums.DominioRecurso;
import br.com.ifsc.aither.backend.model.Permissao;
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
	
	@GetMapping("/permissions")
	public ResponseEntity<List<Permissao>> getPermissions() {
		return ResponseEntity.ok(usuarioService.getPermissions());
	}

	@PostMapping("hasPermission")
	public ResponseEntity<Boolean> hasPermission(String urn, DominioRecurso recurso) {
		return ResponseEntity.ok(this.usuarioService.hasPermission(urn, recurso));
	}
}
