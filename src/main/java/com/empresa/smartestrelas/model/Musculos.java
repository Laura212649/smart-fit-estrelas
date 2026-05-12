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
    private String nome; // Ex: Peitoral maior [cite: 118]

    @Column(nullable = false)
    private String nomeEn; // Ex: Pectoralis major [cite: 118]
}
