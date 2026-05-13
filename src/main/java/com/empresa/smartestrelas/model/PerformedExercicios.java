package com.empresa.smartestrelas.model;



import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "performed_exercises")
public class PerformedExercicios {

    // --- GETTERS E SETTERS (Essenciais para o Service funcionar) ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;

    @ElementCollection
    @CollectionTable(name = "performed_sets", joinColumns = @JoinColumn(name = "performed_exercise_id"))
    private List<PerformedSet> sets = new ArrayList<>();

    // --- CONSTRUTORES ---
    public PerformedExercicios() {}

    public void setId(Long id) { this.id = id; }

    public void setExercicio(Exercicio exercicio) { this.exercicio = exercicio; }

    public void setWorkoutSession(WorkoutSession workoutSession) { this.workoutSession = workoutSession; }

    public void setSets(List<PerformedSet> sets) { this.sets = sets; }
}