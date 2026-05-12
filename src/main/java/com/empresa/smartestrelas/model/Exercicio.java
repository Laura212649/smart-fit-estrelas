package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.aot.generate.GeneratedTypeReference;

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
            name = "exercise_equipment",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipamento> equipamentos;

    // Relacionamento com Músculos Primários [cite: 75, 215, 239]
    @ManyToMany
    @JoinTable(
            name = "exercise_primary_muscles",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id")
    )
    private List<Musculos> primaryMusculos;

    // Relacionamento com Músculos Secundários [cite: 76, 215, 239]
    @ManyToMany
    @JoinTable(
            name = "exercise_secondary_muscles",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id")
    )
    private List<Musculos> secondaryMusculos;
}


