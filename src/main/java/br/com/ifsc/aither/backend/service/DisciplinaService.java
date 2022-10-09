package br.com.ifsc.aither.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.model.DisciplinaDTO;

public interface DisciplinaService {

	DisciplinaDTO create(DisciplinaDTO disciplinaDTO);
	DisciplinaDTO read(Integer id);
	DisciplinaDTO update(DisciplinaDTO disciplinaDTO);
	void delete(Integer id);
	Page<DisciplinaDTO> listAsDTO(String query, Pageable pageable);
	Page<DisciplinaAutoComplete> autoComplete(String query, Pageable pageable);
}
