package com.empresa.smartestrelas.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "nome_usuario")
    private String username;

    @Column(nullable = false, unique = true,name = "email")
    private String email;

    @Column(nullable = false,name = "senha")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "nivel_treino")
    private TrainingLevel trainingLevel;

    //representa a data e hora de criação do registro.
    @Column(nullable = false, updatable = false, name = "criado_em")
    private LocalDateTime createdAt;

    @Column(name = "perfil_acesso")
    private String role;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.role == null) {
            this.role = "USER"; // Define um papel padrão caso esteja vazio
        }
    }
}

