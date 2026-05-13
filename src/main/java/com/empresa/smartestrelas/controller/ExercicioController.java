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
    private ExercicioService exercicioService;

    // POST /exercicios (Admin) - Cadastra um novo exercício
    @PostMapping
    public ResponseEntity<Exercicio> criar(@RequestBody @Valid ExercicioRequest dto) {
        Exercicio novo = exercicioService.salvarComIds(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // GET /exercicios - Lista todos com suporte a filtros
    @GetMapping
    public List<Exercicio> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Long equipamentoId,
            @RequestParam(required = false) Long musculosId) {
        return exercicioService.listarComFiltros(categoria, equipamentoId, musculosId);
    }

    // GET /exercicios/{“id”}
    @GetMapping("/{id}")
    public Exercicio buscar(@PathVariable Long id) {
        return exercicioService.buscarPorId(id);
    }

    // DELETE /exercicios/{id} (Admin)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        exercicioService.excluir(id);
    }
}

