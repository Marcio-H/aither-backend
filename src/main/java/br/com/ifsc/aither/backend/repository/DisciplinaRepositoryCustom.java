package br.com.ifsc.aither.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.domain.Disciplina;

public interface DisciplinaRepositoryCustom {

	Page<DisciplinaAutoComplete> autoComplete(String query, Pageable pageable);
	Page<Disciplina> findAll(String query, Pageable pageable);
}
