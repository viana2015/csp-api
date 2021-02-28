package io.github.viana2015.csp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.viana2015.csp.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
