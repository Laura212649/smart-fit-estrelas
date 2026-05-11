package com.empresa.smartestrelas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Musculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ex: Peitoral maior [cite: 118]

    @Column(nullable = false)
    private String nameEn; // Ex: Pectoralis major [cite: 118]
}
