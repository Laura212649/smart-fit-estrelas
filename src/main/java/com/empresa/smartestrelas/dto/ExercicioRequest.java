package com.empresa.smartestrelas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ExercicioRequest(
        @NotBlank (message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,
        String videoUrl,

        @NotBlank (message = "A categoria é obrigatória")
        String categoria, // Deve ser Abs, Arms, Back, etc.

        @NotEmpty (message = "Pelo menos um equipamento deve ser selecionado")
        List<Long> equipamentoIds,

        @NotEmpty (message = "Pelo menos um músculo primário deve ser selecionado")
        List<Long> primaryMusculosIds,
        List<Long> secondaryMusculosIds
) {}
