package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.dto.ConteudoDTO;
import br.com.ifsc.aither.backend.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/conteudo")
public class ConteudoController {

	@Autowired
	private ConteudoService service;

	@PostMapping("/create")
	public ConteudoDTO create(@RequestBody @Valid ConteudoDTO dto) {
		return service.create(dto);
	}
}
