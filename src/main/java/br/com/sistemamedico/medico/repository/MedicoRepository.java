package br.com.sistemamedico.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedico.medico.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    
}
