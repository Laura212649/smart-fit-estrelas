package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.model.TrainingPlanWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingPlanWeekRepository extends JpaRepository<TrainingPlanWeek, Long> {
}
