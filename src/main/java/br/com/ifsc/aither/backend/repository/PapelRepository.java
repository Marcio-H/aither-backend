package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel, Integer> {

	Optional<Papel> findPapelByDescricao(String descricao);
}
