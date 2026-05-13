package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "exercicios")
@Data
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo; // Título do exercício (ex: Supino Reto)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao; // Instruções de execução [cite: 71]

    private String videoUrl; // Link opcional para vídeo demonstrativo [cite: 72]

    @Column(nullable = false)
    private String categoria; // Categoria (ex: Chest, Legs) [cite: 73]

    // Relacionamento com Equipamentos [cite: 74, 215, 239]
    @ManyToMany
    @JoinTable(
            name = "exercicios_equipamentos",
            joinColumns = @JoinColumn(name = "exercicios_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamentos_id")
    )
    private List<Equipamentos> equipamentos;

    // Relacionamento com Músculos Primários [cite: 75, 215, 239]
    @ManyToMany
    @JoinTable(
            name = "exercicios_primary_musculos",
            joinColumns = @JoinColumn(name = "exercicios_id"),
            inverseJoinColumns = @JoinColumn(name = "musculos_id")
    )
    private List<Musculos> primaryMusculos;

    // Relacionamento com Músculos Secundários [cite: 76, 215, 239]
    @ManyToMany
    @JoinTable(
            name = "exercicios_secondary_musculos",
            joinColumns = @JoinColumn(name = "exercicios_id"),
            inverseJoinColumns = @JoinColumn(name = "musculos_id")
    )
    private List<Musculos> secondaryMusculos;
}


