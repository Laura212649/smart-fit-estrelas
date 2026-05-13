package com.empresa.smartestrelas.repository;

import com.empresa.smartestrelas.model.Musculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusculosRepository  extends JpaRepository<Musculos, Long> {
}
