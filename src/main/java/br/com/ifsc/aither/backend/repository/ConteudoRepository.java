package br.com.ifsc.aither.backend.repository;

import br.com.ifsc.aither.backend.domain.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConteudoRepository extends JpaRepository<Conteudo, Integer> {
}
