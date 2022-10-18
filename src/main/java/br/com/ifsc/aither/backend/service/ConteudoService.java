package br.com.ifsc.aither.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.model.ConteudoDTO;

public interface ConteudoService {

	ConteudoDTO create(ConteudoDTO conteudoDTO);
	ConteudoDTO read(Integer id);
	ConteudoDTO update(ConteudoDTO conteudoDTO);
	void delete(Integer id);
	Page<ConteudoDTO> listAsDTO(String query, Pageable pageable);
	Page<ConteudoAutoComplete> autoComplete(String query, Pageable pageable);
}
