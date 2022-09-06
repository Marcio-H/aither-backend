package br.com.ifsc.aither.backend.controller;

import br.com.ifsc.aither.backend.domain.Papel;
import br.com.ifsc.aither.backend.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/papel")
public class PapelController {

	@Autowired
	private PapelService service;
}
