package com.empresa.smartestrelas.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Muscles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ex: Peitoral maior [cite: 118]

    @Column(nullable = false)
    private String nameEn; // Ex: Pectoralis major [cite: 118]
}
