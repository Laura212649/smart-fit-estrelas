package com.empresa.smartestrelas.model;


import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "execicio_slots")
public class ExercicioSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio; // O exercício do seu catálogo [cite: 166]

    @Setter
    public Integer sets; // Séries [cite: 139]
    @Setter
    public Integer reps; // Repetições [cite: 143]
    @Setter
    public Double weightKg; // Carga sugerida [cite: 144]
    @Setter
    public Integer restSeconds; // Tempo de descanso [cite: 145]

    @Setter
    @ManyToOne
    @JoinColumn(name = "day_id")
    private TrainingPlanDay day;

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
}
