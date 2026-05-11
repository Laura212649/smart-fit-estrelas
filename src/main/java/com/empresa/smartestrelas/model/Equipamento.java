package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Ex: Barbell, Dumbbell, Bench [cite: 60, 111, 115]
}
