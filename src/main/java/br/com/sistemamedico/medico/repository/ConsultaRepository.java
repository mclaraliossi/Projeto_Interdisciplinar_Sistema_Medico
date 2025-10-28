package br.com.sistemamedico.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedico.medico.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    
}
