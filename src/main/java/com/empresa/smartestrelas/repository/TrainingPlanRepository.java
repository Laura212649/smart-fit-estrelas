package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.model.TrainingPlan;
import com.empresa.smartestrelas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
    List<TrainingPlan> findByUsuario(Usuario usuario);
}
