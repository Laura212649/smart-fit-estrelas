package com.empresa.smartestrelas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dias_do_plano_de_treinamento")
public class TrainingPlanDay  {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "dia_da_semana")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; // MONDAY a SUNDAY

    @Getter
    @Setter
    @Column(name = "foco_divisao")
    @Enumerated(EnumType.STRING)
    private SplitFocus splitFocus; // Ex: UPPER_PUSH

    @Setter
    @ManyToOne
    @JoinColumn(name = "semana_id")
    private TrainingPlanWeek semana;

    @Getter
    @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL)
    private List<ExercicioSlot> exercicios = new ArrayList<>();

    public TrainingPlanDay() {}

}



