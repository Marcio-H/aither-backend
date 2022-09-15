package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {

	List<Recurso> findAllByPermiteTodosIsTrue();
}
