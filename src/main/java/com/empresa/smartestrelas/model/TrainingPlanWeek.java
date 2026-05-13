package com.empresa.smartestrelas.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training_plan_weeks")
public class TrainingPlanWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer weekNumber; // Número da semana no plano

    @ManyToOne
    @JoinColumn(name = "training_plan_id", nullable = false)
    private TrainingPlan trainingPlan; // Referência ao plano pai [cite: 134, 135]

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlanDay> days = new ArrayList<>(); // Lista de dias de treino desta semana [cite: 137, 163]

    public TrainingPlanWeek() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getWeekNumber() { return weekNumber; }
    public void setWeekNumber(Integer weekNumber) { this.weekNumber = weekNumber; }

    public TrainingPlan getTrainingPlan() { return trainingPlan; }
    public void setTrainingPlan(TrainingPlan trainingPlan) { this.trainingPlan = trainingPlan; }

    public List<TrainingPlanDay> getDays() { return days; }
    public void setDays(List<TrainingPlanDay> days) { this.days = days; }
}

