package br.com.sistemamedico.medico.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamedico.medico.entity.Consulta;
import br.com.sistemamedico.medico.repository.ConsultaRepository;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository repository;

    public Consulta save(Consulta consulta){

        validarData(consulta);
        validarConflitoAgendaMedico(consulta);
        validarConflitoAgendaPaciente(consulta);
        validarIntervaloMinimo(consulta);

        return repository.save(consulta);
    }

    public void validarData(Consulta consulta) {
        LocalDateTime agora = LocalDateTime.now();

        if (consulta.getDataehoraConsulta() == null) {
            throw new IllegalArgumentException("A data da consulta é obrigatória.");
        }

        if (consulta.getDataehoraConsulta().isBefore(agora)) {
            throw new IllegalArgumentException("A data da consulta não pode ser retroativa.");
        }
    }

    public void validarConflitoAgendaMedico(Consulta consulta) {

        Integer idMedico = consulta.getMedico().getIdMedico();
        LocalDateTime data = consulta.getDataehoraConsulta();

        boolean existe = repository.existsByMedicoIdMedicoAndDataehoraConsulta(idMedico, data);

        if (consulta.getIdConsulta() != null) {
            Consulta atual = repository.findById(consulta.getIdConsulta()).orElse(null);
            if (atual != null &&
                atual.getMedico().getIdMedico().equals(idMedico) &&
                atual.getDataehoraConsulta().equals(data)) {
                return;
            }
        }

        if (existe) {
            throw new IllegalArgumentException(
                "O médico já possui uma consulta agendada nesse horário."
            );
        }
    }

    public void validarConflitoAgendaPaciente(Consulta consulta) {

        Integer idCliente = consulta.getCliente().getIdCliente();
        LocalDateTime data = consulta.getDataehoraConsulta();

        boolean existe = repository.existsByClienteIdClienteAndDataehoraConsulta(idCliente, data);

        if (consulta.getIdConsulta() != null) {
            Consulta atual = repository.findById(consulta.getIdConsulta()).orElse(null);

            if (atual != null &&
                atual.getCliente().getIdCliente().equals(idCliente) &&
                atual.getDataehoraConsulta().equals(data)) {
                return;
            }
        }

        if (existe) {
            throw new IllegalArgumentException(
                "O paciente já possui uma consulta agendada nesse horário."
            );
        }
    }

    public void validarIntervaloMinimo(Consulta consulta) {

        Integer idMedico = consulta.getMedico().getIdMedico();
        LocalDateTime dataNova = consulta.getDataehoraConsulta();

        List<Consulta> consultasMedico = repository.findByMedicoIdMedico(idMedico);

        for (Consulta c : consultasMedico) {

            if (consulta.getIdConsulta() != null && consulta.getIdConsulta().equals(c.getIdConsulta())) {
                continue;
            }

            LocalDateTime dataExistente = c.getDataehoraConsulta();
            long minutos = Duration.between(dataExistente, dataNova).abs().toMinutes();

            if (minutos < 30) {
                throw new IllegalArgumentException(
                    "O médico possui outra consulta próxima desse horário. Deve haver intervalo mínimo de 30 minutos."
                );
            }
        }
    }

    public List<Consulta> findAll(){
        return repository.findAll();
    }

    public Consulta findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
