package io.github.viana2015.csp.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.viana2015.csp.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

	Optional<Usuario> findByUsername(String username);

	boolean existsByUsername(String username);
}
