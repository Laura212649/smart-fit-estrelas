package com.empresa.smartestrelas.repository;



import com.empresa.smartestrelas.model.Goal;
import com.empresa.smartestrelas.model.TrainingPlan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TrainingPlanRepositoryTest {

    @Autowired
    private TrainingPlanRepository repository;

    @Test
    @DisplayName("Deve salvar e recuperar um plano de treino com sucesso")
    void deveSalvarERecuperarPlanoDeTreino() {
        // Arrange
        TrainingPlan plano = new TrainingPlan();
        plano.setName("Hipertrofia ABC");      // Mapeia para a coluna 'nome'
        plano.setGoal(Goal.valueOf("Ganho de massa muscular")); // Mapeia para a coluna 'objetivo'
        plano.setWeekCount(12);                  // Mapeia para a coluna 'quantidade_semanas'

        // Act
        TrainingPlan salvo = repository.save(plano);

        // Assert
        assertNotNull(salvo.getId(), "O ID do plano salvo não deveria ser nulo");
        assertEquals("Hipertrofia ABC", salvo.getName());
        assertEquals("Ganho de massa muscular", salvo.getGoal());
        assertEquals(12, salvo.getWeekCount());
    }
}