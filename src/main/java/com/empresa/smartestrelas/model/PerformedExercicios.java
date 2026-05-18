package com.empresa.smartestrelas.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Exercicios_realizados")
public class PerformedExercicios {

    // --- GETTERS E SETTERS (Essenciais para o Service funcionar) ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "sessao_treino_id")
    private WorkoutSession workoutSession;

    @ElementCollection
    @CollectionTable(name = "Series_Realizadas", joinColumns = @JoinColumn(name = "exercicio_realizado_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name = "reps", column = @Column(name = "repeticoes")),
            @AttributeOverride(name = "weightKg", column = @Column(name = "peso_kg"))
    })
    private List<PerformedSet> sets = new ArrayList<>();


    public PerformedExercicios() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public void setWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSession = workoutSession;
    }

    public void setSets(List<PerformedSet> sets) {
        this.sets = sets;
    }
}