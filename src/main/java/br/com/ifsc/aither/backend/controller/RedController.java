package br.com.ifsc.aither.backend.controller;

import static br.com.ifsc.aither.backend.utils.MessageFormatter.format;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.CREATED;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ifsc.aither.backend.annotations.VerificarPermissaoAtualizar;
import br.com.ifsc.aither.backend.annotations.VerificarPermissaoCriar;
import br.com.ifsc.aither.backend.annotations.VerificarPermissaoRemover;
import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.model.RedDTO;
import br.com.ifsc.aither.backend.service.RedService;

@RestController
@RequestMapping("/red")
public class RedController {

	private static final String RESOURCE = "urn:resource:red";

	@Inject
	private RedService service;

	@VerificarPermissaoCriar
	@PostMapping
	public ResponseEntity<RedDTO> create(@Valid @RequestBody RedDTO redDTO) {
		return ResponseEntity.status(CREATED).body(service.create(redDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RedDTO> read(@PathVariable Integer id) {
		return ResponseEntity.ok(service.read(id));
	}

	@VerificarPermissaoAtualizar
	@PutMapping("/{id}")
	public ResponseEntity<RedDTO> update(@PathVariable Integer id, @Valid @RequestBody RedDTO redDTO) {
		requireNonNull(id, "Id não pode ser nulo.");
		requireNonNull(redDTO, "Red não pode ser nulo.");
		if (!Objects.equals(id, redDTO.getId())) {
			throw new IllegalStateException(format("Id ''{0}'' e red id ''{1}'' devem ser iguais.",
					id, redDTO.getId()));
		}
		return ResponseEntity.ok(service.update(redDTO));
	}

	@VerificarPermissaoAtualizar
	@PutMapping("/{id}/uploadImage")
	public ResponseEntity<RedDTO> uploadImage(@PathVariable Integer id, @ModelAttribute MultipartFile imagem) throws IOException {
		requireNonNull(id, "Id não pode ser nulo.");

		var red = service.read(id);

		red.setImagem(imagem.getBytes());
		return ResponseEntity.ok(service.update(red));
	}

	@VerificarPermissaoRemover
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@GetMapping
	public Page<RedDTO> list(String query, Pageable pageable) {
		return service.listAsDTO(query, pageable);
	}

	@GetMapping("/autoComplete")
	public Page<RedAutoComplete> autoComplete(@RequestParam("query") String query, Pageable pageable) {
		return service.autoComplete(query, pageable);
	}

	public String getResource() {
		return RESOURCE;
	}
}
