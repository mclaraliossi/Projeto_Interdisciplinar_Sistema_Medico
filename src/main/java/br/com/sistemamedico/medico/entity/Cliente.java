package br.com.sistemamedico.medico.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCliente;

    @Column(nullable = false, length = 30)
    private String nomeCliente;

    @Column(nullable = false, length = 30)
    private String ruaCliente;

    @Column(nullable = false, length = 30)
    private String bairroCliente;

    @Column(nullable = false, length = 30)
    private String numeroCliente;

    @Column(nullable = false, length = 30)
    private String estadoCliente;

    @Column(nullable = false, length = 30)
    private String cidadeCliente;

    @Column(nullable = false, length = 30)
    private String cepCliente;

    @Column(nullable = false, length = 11)
    private String telefoneCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Consulta> consultas;

    
}
