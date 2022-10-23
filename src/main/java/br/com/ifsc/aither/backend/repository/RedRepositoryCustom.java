package br.com.ifsc.aither.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.domain.Red;

public interface RedRepositoryCustom {

	Page<RedAutoComplete> autoComplete(String query, Pageable pageable);
	Page<Red> findAll(String query, Pageable pageable);
}
