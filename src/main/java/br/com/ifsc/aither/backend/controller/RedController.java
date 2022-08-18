package br.com.ifsc.aither.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/red")
public class RedController {

	@PostMapping("/create")
	public String create() {
		return "criado";
	}

	@GetMapping("/read")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESSOR')")
	public String read() {
		return "read";
	}
}
