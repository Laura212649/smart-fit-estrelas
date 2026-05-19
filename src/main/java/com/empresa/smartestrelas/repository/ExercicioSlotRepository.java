package com.empresa.smartestrelas.repository;



import com.empresa.smartestrelas.model.ExercicioSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioSlotRepository extends JpaRepository<ExercicioSlot, Long> {
}