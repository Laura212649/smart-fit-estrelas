package com.empresa.smartestrelas.dto;

public record ExercicioSlotResponse(
        Long id,
        Long exerciseId,
        String exerciseTitle,
        Integer sets,
        Integer reps,
        Double weightKg,
        Integer restSeconds
) {
}
