package com.empresa.smartestrelas.dto;

import com.empresa.smartestrelas.model.TrainingLevel;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String username;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Informe um e-mail válido")
    private String email;

   @NotBlank(message = "A senha é obrigatória")
   @Size(min = 8)
    private String password;

   @NotNull(message = "Nível de treino é obrigatório")
   private TrainingLevel trainingLevel;

    private String role;
}
