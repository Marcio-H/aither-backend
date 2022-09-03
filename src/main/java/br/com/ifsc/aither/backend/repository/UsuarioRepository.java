package br.com.ifsc.aither.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsc.aither.backend.domain.usuario.Usuario;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	Optional<Usuario> findByEmail(String email);
}
