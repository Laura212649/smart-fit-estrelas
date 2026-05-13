package com.empresa.smartestrelas.dto;

public record ExerciciosSlotRequest(
        Long exerciciosId, // ID do exercício do catálogo [cite: 166]
        Integer sets,
        Integer reps,
        Double weightKg, // Opcional no request [cite: 166]
        Integer restSeconds
) {}
