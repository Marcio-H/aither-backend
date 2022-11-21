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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsc.aither.backend.annotations.VerificarPermissaoAtualizar;
import br.com.ifsc.aither.backend.annotations.VerificarPermissaoCriar;
import br.com.ifsc.aither.backend.annotations.VerificarPermissaoRemover;
import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.model.DisciplinaDTO;
import br.com.ifsc.aither.backend.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

	private static final String RESOURCE = "urn:resource:disciplina";

	@Inject
	private DisciplinaService service;

	@VerificarPermissaoCriar
	@PostMapping
	public ResponseEntity<DisciplinaDTO> create(@Valid @ModelAttribute DisciplinaDTO disciplinaDTO) {
		return ResponseEntity.status(CREATED).body(service.create(disciplinaDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(service.read(id));
	}

	@VerificarPermissaoAtualizar
	@PutMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> update(@PathVariable Integer id, @Valid @ModelAttribute DisciplinaDTO disciplinaDTO) {
		requireNonNull(id, "Id não pode ser nulo.");
		requireNonNull(disciplinaDTO, "Disciplina não pode ser nula.");
		if (!Objects.equals(id, disciplinaDTO.getId())) {
			throw new IllegalStateException(format("Id ''{0}'' e disciplina id ''{1}'' devem ser iguais.",
					id, disciplinaDTO.getId()));
		}
		return ResponseEntity.ok(service.update(disciplinaDTO));
	}

	@VerificarPermissaoRemover
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@GetMapping
	public Page<DisciplinaDTO> list(String query, Pageable pageable) {
		return service.listAsDTO(query, pageable);
	}

	@GetMapping("/autoComplete")
	public Page<DisciplinaAutoComplete> autoComplete(String query, Pageable pageable) {
		return service.autoComplete(query, pageable);
	}

	public String getResource() {
		return RESOURCE;
	}
}
