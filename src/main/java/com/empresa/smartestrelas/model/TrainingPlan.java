package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Planos_Treino")
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Relacionamento para identificar o dono do plano [cite: 152]


    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "estrutura_treino", columnDefinition = "jsonb")
    private List<TrainingPlanWeek> weeks;

}