package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.RecursoBackend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoBackendRepository extends JpaRepository<RecursoBackend, Integer> {
}
