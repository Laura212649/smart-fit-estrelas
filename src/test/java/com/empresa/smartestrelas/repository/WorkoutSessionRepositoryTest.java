package com.empresa.smartestrelas.repository;



import com.empresa.smartestrelas.model.WorkoutSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WorkoutSessionRepositoryTest {

    @Autowired
    private WorkoutSessionRepository repository;

    @Test
    @DisplayName("Deve salvar e recuperar uma sessão de treino com a data atual")
    void deveSalvarERecuperarSessaoTreino() {
        // Arrange
        WorkoutSession sessao = new WorkoutSession();
        LocalDate dataEsperada = LocalDate.now();
        sessao.setData(dataEsperada); // Testa a coluna 'data' em português

        // Act
        WorkoutSession salva = repository.save(sessao);

        // Assert
        assertNotNull(salva.getId(), "O ID da sessão de treino não deveria ser nulo");
        assertEquals(dataEsperada, salva.getData(), "A data salva deve ser igual à data esperada");
    }
}
