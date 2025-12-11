package br.com.sistemamedico.medico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedico.medico.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
     Optional<Usuario> findByLoginUsuario(String loginUsuario);
}
