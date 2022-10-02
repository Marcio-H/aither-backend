package br.com.ifsc.aither.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsc.aither.backend.domain.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
}
