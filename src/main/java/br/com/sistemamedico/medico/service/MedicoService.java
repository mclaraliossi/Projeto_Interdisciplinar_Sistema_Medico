package br.com.sistemamedico.medico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamedico.medico.entity.Medico;
import br.com.sistemamedico.medico.repository.MedicoRepository;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository repository;

    public Medico save(Medico medico){
        return repository.save(medico);
    }

    public List<Medico> findAll(){
        return repository.findAll();
    }

    public Medico findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
