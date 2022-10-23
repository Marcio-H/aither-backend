package br.com.ifsc.aither.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.model.RedDTO;

public interface RedService {

	RedDTO create(RedDTO redDTO);
	RedDTO read(Integer id);
	RedDTO update(RedDTO redDTO);
	void delete(Integer id);
	Page<RedDTO> listAsDTO(String query, Pageable pageable);
	Page<RedAutoComplete> autoComplete(String query, Pageable pageable);
}
