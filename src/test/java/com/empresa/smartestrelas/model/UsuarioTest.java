package com.empresa.smartestrelas.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    @DisplayName("Deve definir perfil padrão como USER e preencher data de criação ao salvar")
    void deveAplicarRegrasPrePersist() {
        // Arrange (Preparação do cenário)
        Usuario usuario = new Usuario();
        usuario.setUsername("marcelo_treino");
        usuario.setEmail("marcelo@email.com");
        usuario.setPassword("12345678");


        usuario.onCreate();


        assertNotNull(usuario.getCreatedAt(), "A data de criação não deveria ser nula");
        assertTrue(usuario.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertEquals("USER", usuario.getRole(), "O perfil padrão deveria ser 'USER'");
    }
}
