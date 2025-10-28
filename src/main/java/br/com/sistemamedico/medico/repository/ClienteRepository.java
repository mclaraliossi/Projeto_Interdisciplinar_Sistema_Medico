package br.com.sistemamedico.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedico.medico.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
