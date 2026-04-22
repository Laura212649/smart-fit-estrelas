package com.empresa.smartestrelas.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Informe um e-mail válido")
    private String email;

    //(?=.*[A-Za-z]) → Tem pelo menos uma letra (maiúscula ou minúscula)
    //(?=.*\\d) → Tem pelo menos um dígito
    //[A-Za-z\\d]{8,} → composta apenas por letras e números, mínimo 8 caracteres
   @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp =  "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "A senha deve ter no mínimo 8 caracteres, deve conter letras e números")
    private String password;
}
