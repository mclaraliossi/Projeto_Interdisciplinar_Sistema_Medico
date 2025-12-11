package br.com.sistemamedico.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sistemamedico.medico.entity.Usuario;
import br.com.sistemamedico.medico.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/criar")
    public String criarForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuario/usuarioFormulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario){
        usuarioService.save(usuario);
        return "/login";
    }
}
