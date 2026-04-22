package com.empresa.smartestrelas.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String name;
    private String email;
    private TrainingLevel trainingLevel;
    private LocalDateTime createdAt;

    public UsuarioResponse(Usuario usuario) {
    }
}

