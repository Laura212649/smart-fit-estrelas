package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Sessoes_treino")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data; // Data em que o treino foi realizado

    @ManyToOne
    @JoinColumn(name = "dia_plano_treino_id")
    private TrainingPlanDay trainingPlanDay; // Referência ao dia do plano

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Usuário que realizou o treino

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL)
    private List<PerformedExercicios> performedExercicios = new ArrayList<PerformedExercicios>();// Lista de exercícios feitos

    public void setDate(LocalDate date) {
        this.data = date;
    }

}
