package com.empresa.smartestrelas.repository;


import com.empresa.smartestrelas.model.Equipamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamentos, Long> {
}
