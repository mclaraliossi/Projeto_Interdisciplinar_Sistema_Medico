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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.sistemamedico.medico.entity.Cliente;
import br.com.sistemamedico.medico.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public String Listar(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "cliente/clienteListar";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/clienteFormulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente,
            @RequestParam("fotoPerfil") MultipartFile file) {

        try {
            if (!file.isEmpty()) {
                String folder = "uploads/fotos/";
                String caminho = folder + file.getOriginalFilename();

                // Cria pasta caso não exista
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get(folder));

                // Salva a imagem na pasta
                java.nio.file.Path path = java.nio.file.Paths.get(caminho);
                file.transferTo(path.toFile());

                // Salva no banco apenas o caminho
                cliente.setFotoCliente(caminho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        clienteService.save(cliente);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.findById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/clienteFormulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/listar";
    }
}
