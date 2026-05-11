package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    // Filtra exercícios por categoria (ex: Chest, Legs) [cite: 77]
    List<Exercicio> findByCategory(String category);

    // Filtra exercícios que contenham um equipamento específico [cite: 77]
    List<Exercicio> findByEquipment_Id(Long equipmentId);

    // Filtra exercícios que tenham um músculo primário específico [cite: 77]
    List<Exercicio> findByPrimaryMuscles_Id(Long muscleId);
}

