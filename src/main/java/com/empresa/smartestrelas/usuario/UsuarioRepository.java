package com.empresa.smartestrelas.usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    // O Optional evita o NullPointerException (erro que acontece no Java quando o
    // seu código tenta usar uma referência de objeto que está "vazia")
    // obrigando quem chama o metodo a tratar o caso onde o usuario não existe

}

