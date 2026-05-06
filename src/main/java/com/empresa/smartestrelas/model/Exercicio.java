package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.aot.generate.GeneratedTypeReference;

@Entity
@Table(name = "exercicos")
@Data
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String grupoMuscular;
    private Integer series;
    private Integer repeticoes;
    private  double carga;
}

