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

import br.com.sistemamedico.medico.entity.Consulta;
import br.com.sistemamedico.medico.service.ClienteService;
import br.com.sistemamedico.medico.service.ConsultaService;
import br.com.sistemamedico.medico.service.MedicoService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaService service;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public String Listar(Model model){
        List<Consulta> consultas = service.findAll();
        model.addAttribute("consultas", consultas);
        return "consulta/consultaListar";
    }

    @GetMapping("/criar")
    public String criarForm(Model model){
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("medicos", medicoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        return "consulta/consultaFormulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Consulta consulta){
        service.save(consulta);
        return "redirect:/consultas/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model){
        Consulta consulta = service.findById(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("medicos", medicoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        return "consulta/consultaFormulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        service.deleteById(id);
        return "redirect:/consultas/listar";
    }
}
