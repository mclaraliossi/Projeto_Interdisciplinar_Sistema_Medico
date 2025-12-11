package br.com.sistemamedico.medico.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sistemamedico.medico.entity.Medico;
import br.com.sistemamedico.medico.service.MedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
    
    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public String Listar(Model model){
        List<Medico> medicos = medicoService.findAll();
        model.addAttribute("medicos", medicos);
        return "medico/medicoListar";
    }

    @GetMapping("/criar")
    public String criarForm(Model model){
        model.addAttribute("medico", new Medico());
        return "medico/medicoFormulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Medico medico){
        medicoService.save(medico);
        return "redirect:/medicos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model){
        Medico medico = medicoService.findById(id);
        model.addAttribute("medico", medico);
        return "medico/medicoFormulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        medicoService.deleteById(id);
        return "redirect:/medicos/listar";
    }
}
