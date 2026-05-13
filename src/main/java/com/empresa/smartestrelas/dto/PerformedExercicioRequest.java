package com.empresa.smartestrelas.dto;

import java.util.List;

public record PerformedExercicioRequest(
        Long exerciseId, // ID do exercício do catálogo
        List<PerformedSetRequest> sets // Lista de séries executadas
) {}
