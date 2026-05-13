package com.empresa.smartestrelas.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_sessions")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Data em que o treino foi realizado

    @ManyToOne
    @JoinColumn(name = "training_plan_day_id")
    private TrainingPlanDay trainingPlanDay; // Referência ao dia do plano [cite: 173, 181]

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Usuário que realizou o treino

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL)
    private List<PerformedExercicios> performedExercicios = new ArrayList<PerformedExercicios>();// Lista de exercícios feitos

    public List<PerformedExercicios> getPerformedExercises() {
        return performedExercicios;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TrainingPlanDay getTrainingPlanDay() {
        return trainingPlanDay;
    }

    public void setTrainingPlanDay(TrainingPlanDay trainingPlanDay) {
        this.trainingPlanDay = trainingPlanDay;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PerformedExercicios> getPerformedExercicios() {
        return performedExercicios;
    }

    public void setPerformedExercicios(List<PerformedExercicios> performedExercicios) {
        this.performedExercicios = performedExercicios;
    }
}
