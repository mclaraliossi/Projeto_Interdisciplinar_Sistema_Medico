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
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMedico;

    @Column(nullable = false, length = 30)
    private String nomeMedico;

    @Column(nullable = false, length = 20)
    private String especialidadeMedico;

    private Integer crmMedico;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
