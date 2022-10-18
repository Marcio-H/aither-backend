package br.com.ifsc.aither.backend.controller;

import static br.com.ifsc.aither.backend.utils.MessageFormatter.format;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Objects;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsc.aither.backend.annotations.VerificarPermissaoAtualizar;
import br.com.ifsc.aither.backend.annotations.VerificarPermissaoCriar;
import br.com.ifsc.aither.backend.annotations.VerificarPermissaoRemover;
import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.model.ConteudoDTO;
import br.com.ifsc.aither.backend.service.ConteudoService;

@RestController
@RequestMapping("/conteudo")
public class ConteudoController {

	private static final String RESOURCE = "urn:resource:conteudo";

	@Inject
	private ConteudoService service;

	@VerificarPermissaoCriar
	@PostMapping
	public ResponseEntity<ConteudoDTO> create(@Valid @RequestBody ConteudoDTO conteudoDTO) {
		return ResponseEntity.status(CREATED).body(service.create(conteudoDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConteudoDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(service.read(id));
	}

	@VerificarPermissaoAtualizar
	@PutMapping("/{id}")
	public ResponseEntity<ConteudoDTO> update(@PathVariable Integer id, @Valid @RequestBody ConteudoDTO conteudoDTO) {
		requireNonNull(id, "Id não pode ser nulo.");
		requireNonNull(conteudoDTO, "Conteúdo não pode ser nulo.");
		if (!Objects.equals(id, conteudoDTO.getId())) {
			throw new IllegalStateException(format("Id ''{0}'' e conteúdo id ''{1}'' devem ser iguais.",
					id, conteudoDTO.getId()));
		}
		return ResponseEntity.ok(service.update(conteudoDTO));
	}

	@VerificarPermissaoRemover
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@GetMapping
	public Page<ConteudoDTO> list(String query, Pageable pageable) {
		return service.listAsDTO(query, pageable);
	}

	@GetMapping("/autoComplete")
	public Page<ConteudoAutoComplete> autoComplete(@RequestParam("query") String query, Pageable pageable) {
		return service.autoComplete(query, pageable);
	}

	public String getResource() {
		return RESOURCE;
	}
}
