package br.com.sistemamedico.medico.service;

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
        return repository.save(consulta);
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
