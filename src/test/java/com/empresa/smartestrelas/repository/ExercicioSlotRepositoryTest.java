package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.model.ExercicioSlot;

// IMPORTS DO SPRING E JUNIT
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ExercicioSlotRepositoryTest {

    @Autowired
    private ExercicioSlotRepository repository;

    @Test
    @DisplayName("Deve salvar e recuperar um slot de exercício com sucesso no banco")
    void deveSalvarERecuperarSlot() {

        ExercicioSlot slot = new ExercicioSlot();
        slot.setSets(4);
        slot.setReps(12);
        slot.setWeightKg(80.0);
        slot.setRestSeconds(90);


        ExercicioSlot salvo = repository.save(slot);


        assertNotNull(salvo.getId());
        assertEquals(4, salvo.getSets());
        assertEquals(80.0, salvo.getWeightKg());
    }
}
