package com.empresa.smartestrelas.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record WorkoutSessionRequest(
        @NotNull(message = "O ID do dia do plano é obrigatório")
        Long trainingPlanDayId, // ID que liga ao dia planejado [cite: 181]

        @NotNull(message = "A data da sessão é obrigatória")
        LocalDate date, // Data da execução [cite: 182]

        @NotNull(message = "A lista de exercícios realizados não pode ser nula")
        List<PerformedExercicioRequest> exercises // Lista dos exercícios feitos [cite: 183]
) {}
