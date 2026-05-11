package com.empresa.smartestrelas.controller;


import com.empresa.smartestrelas.dto.ExercicioRequest;
import com.empresa.smartestrelas.model.Exercicio;
import com.empresa.smartestrelas.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {
    @Autowired
    private ExercicioService service;

    // POST /exercises (Admin) - Cadastra um novo exercício [cite: 69]
    @PostMapping
    public ResponseEntity<Exercicio> criar(@RequestBody @Valid ExercicioRequest dto) {
        Exercicio novo = service.salvarComIds(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // GET /exercises - Lista todos com suporte a filtros [cite: 77]
    @GetMapping
    public List<Exercicio> listar(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long equipmentId,
            @RequestParam(required = false) Long muscleId) {
        return service.listarComFiltros(category, equipmentId, muscleId);
    }

    // GET /exercises/{id} - Detalhes de um exercício [cite: 79]
    @GetMapping("/{id}")
    public Exercicio buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // DELETE /exercises/{id} (Admin) [cite: 80]
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}

