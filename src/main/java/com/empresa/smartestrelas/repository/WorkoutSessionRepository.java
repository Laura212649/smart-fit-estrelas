package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.dto.PersonalRecordResponse;
import com.empresa.smartestrelas.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    @Query("SELECT new com.empresa.smartestrelas.dto.PersonalRecordResponse(" +
            "pe.exercicio.id, pe.exercicio.titulo, MAX(s.weightKg), MAX(s.reps), ws.date) " +
            "FROM WorkoutSession ws JOIN ws.performedExercises pe JOIN pe.sets s " +
            "WHERE pe.exercicio.id = :exerciseId AND ws.usuario.id = :userId " +
            "GROUP BY pe.exercicio.id, pe.exercicio.titulo, ws.date " +
            "ORDER BY MAX(s.weightKg) DESC")
    Optional<PersonalRecordResponse> findMaxWeightByExerciseAndUser(Long exerciseId, Long userId);
}
