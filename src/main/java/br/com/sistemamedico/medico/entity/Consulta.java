package br.com.sistemamedico.medico.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConsulta;

    private LocalDateTime dataehoraConsulta;

    @Column(nullable = false, length = 50)
    private String observacoesConsulta;
    
    @ManyToOne
    @JoinColumn(name = "idMedico_fk")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "idCliente_fk")
    private Cliente cliente;

}
