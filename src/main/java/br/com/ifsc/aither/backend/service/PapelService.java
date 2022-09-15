package br.com.ifsc.aither.backend.service;

import br.com.ifsc.aither.backend.domain.Papel;

import java.util.Optional;

public interface PapelService {

	Papel create(Papel papel);

	Optional<Papel> findPapelByDescricao(String descricao);
}
