package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PapelRepository extends JpaRepository<Papel, Integer> {
}
