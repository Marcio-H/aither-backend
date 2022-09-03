package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.dto.RedDTO;
import br.com.ifsc.aither.backend.service.RedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/red")
public class RedController {

	@Autowired
	private RedService service;

	@PreAuthorize("!hasAuthority('NULL_ROLE')")
	@PostMapping("/create")
	public RedDTO create(@RequestBody @Valid RedDTO dto) {
		return service.create(dto);
	}
}
