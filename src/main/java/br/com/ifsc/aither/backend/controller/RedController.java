package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.service.RedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/red")
public class RedController {

	@Autowired
	private RedService service;

	@PostMapping("/create")
	public Red create(@RequestBody @Valid Red red) {
		return service.create(red);
	}
}
