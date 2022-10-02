package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>, DisciplinaRepositoryCustom {
}
