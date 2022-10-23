package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Red;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedRepository extends JpaRepository<Red, Integer>, RedRepositoryCustom {
}
