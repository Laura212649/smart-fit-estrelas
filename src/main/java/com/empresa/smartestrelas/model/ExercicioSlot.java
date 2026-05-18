package com.empresa.smartestrelas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercicios_slots")
public class ExercicioSlot {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "exercicios_id")
    private Exercicio exercicio;

    @Getter
    @Setter
    @Column(name = "series")
    public Integer sets; // Séries

    @Getter
    @Setter
    @Column(name = "repeticoes")
    public Integer reps; // Repetições

    @Getter
    @Setter
    @Column(name = "peso_kg")
    public Double weightKg; // Carga sugerida

    @Getter
    @Setter
    @Column(name = "segundos_descanso")
    public Integer restSeconds; // Tempo de descanso

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

    public Long exerciciosId() {
        return null;

    }

}
