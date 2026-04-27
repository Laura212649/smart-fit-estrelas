package com.empresa.smartestrelas.controller;

import com.empresa.smartestrelas.model.Usuario;
import com.empresa.smartestrelas.dto.UsuarioRequest;
import com.empresa.smartestrelas.dto.UsuarioResponse;
import com.empresa.smartestrelas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.criar(request));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodos();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
}

