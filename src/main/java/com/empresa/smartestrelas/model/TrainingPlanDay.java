package com.empresa.smartestrelas.model;


import jakarta.persistence.*;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dias_do_plano_de_treinamento")
public class TrainingPlanDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; // MONDAY a SUNDAY [cite: 164]

    @Setter
    @Enumerated(EnumType.STRING)
    private SplitFocus splitFocus; // Ex: UPPER_PUSH

    @Setter
    @ManyToOne
    @JoinColumn(name = "week_id")
    private TrainingPlanWeek week;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<ExercicioSlot> exercicios = new ArrayList<>();

    public TrainingPlanDay() {}

    // Também é bom adicionar o Getter para a lista de exercícios
    public List<ExercicioSlot> getExercises() {
        return exercicios;
    }
}



