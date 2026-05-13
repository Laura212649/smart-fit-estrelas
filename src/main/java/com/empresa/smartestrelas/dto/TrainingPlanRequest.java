package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.Goal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TrainingPlanRequest(
        @NotBlank(message = "O nome do plano é obrigatório")
        String name,

        @NotNull(message = "O objetivo é obrigatório (WEIGHT_LOSS, MUSCLE_GAIN, CONDITIONING)")
        Goal goal,

        @NotNull(message = "O número de semanas é obrigatório")
        Integer weekCount,

        @NotNull(message = "A lista de semanas não pode ser nula")
        List<TrainingPlanWeekRequest> weeks
) {}

