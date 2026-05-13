package com.empresa.smartestrelas.dto;

import java.util.List;

public record ExercicioResponse (
        Long id,
        String titulo,
        String descricao,
        String videoUrl,
        String categoria,
        List<EquipamentosResponse> equipamento,
        List<MusculosResponse> primaryMusculos,
        List<MusculosResponse> secondaryMusculos
){}
