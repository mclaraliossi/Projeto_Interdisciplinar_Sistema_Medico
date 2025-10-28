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

import br.com.sistemamedico.medico.entity.Cliente;
import br.com.sistemamedico.medico.service.ClienteService;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService service;

    @GetMapping("/listar")
    public String Listar(Model model){
        List<Cliente> clientes = service.findAll();
        model.addAttribute("clientes", clientes);
        return "cliente/clienteListar";
    }

    @GetMapping("/criar")
    public String criarForm(Model model){
        model.addAttribute("cliente", new Cliente());
        return "cliente/clienteFormulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente){
        service.save(cliente);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model){
        Cliente cliente = service.findById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/clienteFormulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        service.deleteById(id);
        return "redirect:/clientes/listar";
    }
}
