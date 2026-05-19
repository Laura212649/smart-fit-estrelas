package com.empresa.smartestrelas.repository;



import com.empresa.smartestrelas.model.TrainingPlanWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TrainingPlanWeekRepositoryTest {

    @Autowired
    private TrainingPlanWeekRepository repository;

    @Test
    @DisplayName("Deve salvar e recuperar uma semana do plano de treino com sucesso")
    void deveSalvarERecuperarSemana() {
        // Arrange
        TrainingPlanWeek semana = new TrainingPlanWeek();
        semana.setWeekNumber(1);

        // Act
        TrainingPlanWeek salva = repository.save(semana);

        // Assert
        assertNotNull(salva.getId(), "O ID não deveria ser nulo após salvar");
        assertEquals(1, salva.getWeekNumber(), "O número da semana deve ser igual a 1");
    }
}
