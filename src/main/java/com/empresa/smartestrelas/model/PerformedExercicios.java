package com.empresa.smartestrelas.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performed_exercicios")
public class PerformedExercicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercicio exercicio; // O exercício do catálogo [cite: 185]

    @ElementCollection
    @CollectionTable(name = "performed_sets", joinColumns = @JoinColumn(name = "performed_exercise_id"))
    private List<PerformedSet> sets = new ArrayList<>(); // Lista de séries [cite: 186]

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;
}

@Embeddable
public record PerformedSet(Integer reps, Double weightKg) {} // Registro de repetições e peso real

