package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.TrainingLevel;
import java.time.LocalDateTime;

public record UsuarioResponse(Long id, String userName, String email, TrainingLevel trainingLevel, LocalDateTime createdAt) {
}

