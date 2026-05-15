package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.dto.PersonalRecordResponse;
import com.empresa.smartestrelas.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    @Query("SELECT new com.empresa.smartestrelas.dto.PersonalRecordResponse(" +
            "pe.exercicio.id, " +
            "pe.exercicio.titulo, " +
            "MAX(s.weightKg), " +
            "CAST(MAX(s.reps) AS long), " +
            "ws.data) " +      // ALTERADO: de ws.date para ws.data para bater com sua Entity
            "FROM WorkoutSession ws " +
            "JOIN ws.performedExercicios pe " + // ALTERADO: para bater com performedExercicios
            "JOIN pe.sets s " +
            "WHERE pe.exercicio.id = :exerciseId AND ws.usuario.id = :userId " +
            "GROUP BY pe.exercicio.id, pe.exercicio.titulo, ws.data")

    Optional<PersonalRecordResponse> findMaxWeightByExerciseAndUser(
            @Param("exerciseId") Long exerciseId,
            @Param("userId") Long userId
    );
}
