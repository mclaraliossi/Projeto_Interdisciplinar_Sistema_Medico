package br.com.sistemamedico.medico.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistemamedico.medico.dto.MedicoConsulta;
import br.com.sistemamedico.medico.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

        boolean existsByMedicoIdMedicoAndDataehoraConsulta(Integer idMedico, LocalDateTime dataehoraConsulta);

        boolean existsByClienteIdClienteAndDataehoraConsulta(Integer idCliente, LocalDateTime dataehoraConsulta);

        List<Consulta> findByMedicoIdMedico(Integer idMedico);

        // Consulta que retorna todas as consultas com informações do médico e cliente
        @Query("SELECT new br.com.sistemamedico.medico.dto.MedicoConsulta(c.cliente.nomeCliente, c.medico.nomeMedico, c.medico.especialidadeMedico, c.dataehoraConsulta) FROM Consulta c ORDER BY c.medico.nomeMedico")
        List<MedicoConsulta> buscarNomeMedico();

        // Consulta filtrada por médico específico
        @Query("SELECT new br.com.sistemamedico.medico.dto.MedicoConsulta(c.cliente.nomeCliente, c.medico.nomeMedico, c.medico.especialidadeMedico, c.dataehoraConsulta) FROM Consulta c WHERE c.medico.idMedico = :idMedico ORDER BY c.dataehoraConsulta")

        List<MedicoConsulta> buscarConsultasPorMedico(Integer idMedico);

}
