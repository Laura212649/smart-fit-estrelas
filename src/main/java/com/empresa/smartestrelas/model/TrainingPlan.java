package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Planos_Treino")
public class TrainingPlan {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotBlank(message = "O nome do plano é obrigatório")
    @Column(name = "nome")
    private String name;

    @NotNull(message = "O objetivo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "objetivo")
    private Goal goal;

    @NotNull(message = "O número de semanas é obrigatório")
    @Column(name = "quantidade_semanas")
    private Integer weekCount;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Relacionamento para identificar o dono do plano [cite: 152]

    @Setter
    @Getter
    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlanWeek> weeks = new ArrayList<>(); // Lista de semanas do plano

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
