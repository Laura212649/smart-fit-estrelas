package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Semanas_Plano_Treino")
public class TrainingPlanWeek {
    // Getters e Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semanas_plano_treino", nullable = false)
    private Integer weekNumber; // Número da semana no plano

    @ManyToOne
    @JoinColumn(name = "plano_treino_id", nullable = false)
    private TrainingPlan trainingPlan;

    @OneToMany(mappedBy = "semana", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlanDay> dias = new ArrayList<>();

    public TrainingPlanWeek() {
    }

}

