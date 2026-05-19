package com.empresa.smartestrelas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "execicio_slots")
public class ExercicioSlot {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio; // O exercício do seu catálogo [cite: 166]

    @Setter
    @Getter
    @Column(name = "series")
    public Integer sets;


    @Setter
    @Getter
    @Column(name = "repeticoes")
    public Integer reps;

    @Column(name = "peso_kg")
    @Setter
    @Getter
    public Double weightKg;


    @Setter
    @Getter
    @Column(name = "segundos_descanso")
    public Integer restSeconds; // Tempo de descanso [cite: 145]

    @Setter
    @ManyToOne
    @JoinColumn(name = "dia_id")
    private TrainingPlanDay dia;

    public ExercicioSlot() {}


    public Integer sets() {
        return null;
    }

    public Integer reps() {
        return null;
    }

    public Double weightKg() {
        return null;
    }

    public Integer restSeconds() {
        return null;
    }

    public Long exercicios() {
        return null;
    }

    public Long exerciciosId() {
        return  null;
    }
}
