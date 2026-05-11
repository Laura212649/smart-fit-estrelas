package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.model.Muscles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleRepository  extends JpaRepository<Muscles, Long> {
}
