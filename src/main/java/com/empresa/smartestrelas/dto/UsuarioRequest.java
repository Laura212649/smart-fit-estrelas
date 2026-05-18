package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.TrainingLevel;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String username;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8,message = "A senha deve conter pelo menos 8 caracteres")
    private String password;

    @NotNull(message = "Nível de treino é obrigatório")
    private TrainingLevel trainingLevel;

    private String role;

}
