package com.empresa.smartestrelas.model;


import jakarta.persistence.*;

@Entity
@Table(name = "execicio_slots")
public class ExercicioSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio; // O exercício do seu catálogo [cite: 166]

    private Integer sets; // Séries [cite: 139]
    private Integer reps; // Repetições [cite: 143]
    private Double weightKg; // Carga sugerida [cite: 144]
    private Integer restSeconds; // Tempo de descanso [cite: 145]

    @ManyToOne
    @JoinColumn(name = "day_id")
    private TrainingPlanDay day;
}
