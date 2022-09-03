package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.dto.DisciplinaDTO;
import br.com.ifsc.aither.backend.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	private DisciplinaService service;

	@PostMapping(value = "/create")
	public DisciplinaDTO create(@ModelAttribute DisciplinaDTO dto) {
		return service.create(dto);
	}
}
