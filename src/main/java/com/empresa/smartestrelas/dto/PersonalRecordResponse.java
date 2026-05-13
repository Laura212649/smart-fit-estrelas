package com.empresa.smartestrelas.dto;

import java.time.LocalDate;

public record PersonalRecordResponse(
        Long exercicioId,
        String exercicioTitulo,
                Double maxWeightKg,
                Integer maxReps,
                LocalDate achievedAt
) {}
