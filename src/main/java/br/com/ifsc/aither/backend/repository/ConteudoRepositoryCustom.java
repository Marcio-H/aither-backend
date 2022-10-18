package br.com.ifsc.aither.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.domain.Conteudo;

public interface ConteudoRepositoryCustom {

	Page<ConteudoAutoComplete> autoComplete(String query, Pageable pageable);
	Page<Conteudo> findAll(String query, Pageable pageable);
}
