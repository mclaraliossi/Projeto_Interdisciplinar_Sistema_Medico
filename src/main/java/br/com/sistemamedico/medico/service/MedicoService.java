package br.com.sistemamedico.medico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamedico.medico.entity.Medico;
import br.com.sistemamedico.medico.repository.MedicoRepository;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico save(Medico medico){
        return medicoRepository.save(medico);
    }

    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }

    public Medico findById(Integer id){
        return medicoRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id){
        medicoRepository.deleteById(id);
    }
}
