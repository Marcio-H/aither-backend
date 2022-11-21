package br.com.ifsc.aither.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.ifsc.aither.backend.domain.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>, QuerydslPredicateExecutor<Disciplina> {
}
