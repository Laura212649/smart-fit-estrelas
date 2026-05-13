package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training_plans")
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do plano é obrigatório") // Requisito de campo obrigatório [cite: 160]
    private String name;

    @NotNull(message = "O objetivo é obrigatório") // Deve aceitar WEIGHT_LOSS, MUSCLE_GAIN ou CONDITIONING [cite: 161]
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @NotNull(message = "O número de semanas é obrigatório") // [cite: 162]
    private Integer weekCount;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Relacionamento para identificar o dono do plano [cite: 152]

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlanWeek> weeks = new ArrayList<>(); // Lista de semanas do plano

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @NotNull(message = "O objetivo é obrigatório") Goal getGoal() {
        return goal;
    }

    public void setGoal(@NotNull(message = "O objetivo é obrigatório") Goal goal) {
        this.goal = goal;
    }

    public @NotNull(message = "O número de semanas é obrigatório") Integer getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(@NotNull(message = "O número de semanas é obrigatório") Integer weekCount) {
        this.weekCount = weekCount;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<TrainingPlanWeek> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<TrainingPlanWeek> weeks) {
        this.weeks = weeks;
    }

    public TrainingPlan(Long id, String name, Goal goal, Integer weekCount, Usuario usuario, List<TrainingPlanWeek> weeks) {
        this.id = id;
        this.name = name;
        this.goal = goal;
        this.weekCount = weekCount;
        this.usuario = usuario;
        this.weeks = weeks;

    }
    public TrainingPlan() {
    }
    }
