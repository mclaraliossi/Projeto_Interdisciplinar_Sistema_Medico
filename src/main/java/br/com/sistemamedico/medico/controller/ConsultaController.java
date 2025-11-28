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

import br.com.sistemamedico.medico.dto.MedicoConsulta;
import br.com.sistemamedico.medico.entity.Consulta;
import br.com.sistemamedico.medico.service.ClienteService;
import br.com.sistemamedico.medico.service.ConsultaService;
import br.com.sistemamedico.medico.service.MedicoService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public String Listar(Model model){
        List<Consulta> consultas = consultaService.findAll();
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
    public String salvar(@ModelAttribute Consulta consulta, Model model){
        try {
            consultaService.save(consulta);
            return "redirect:/consultas/listar";
    
        } catch (IllegalArgumentException e) {
    
            model.addAttribute("erro", e.getMessage());
    
            // Recarregar as listas do formulário
            model.addAttribute("medicos", medicoService.findAll());
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("consulta", consulta);
    
            return "consulta/consultaFormulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model){
        Consulta consulta = consultaService.findById(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("medicos", medicoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        return "consulta/consultaFormulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        consultaService.deleteById(id);
        return "redirect:/consultas/listar";
    }

    @GetMapping("/listar-nome-medico")
    public String listarNomeMedico(Model model){
        List<MedicoConsulta> consultas = consultaService.buscarNomeMedico();
        model.addAttribute("consultas", consultas);
        return "consulta/listaMedicoNome";
    }
}
