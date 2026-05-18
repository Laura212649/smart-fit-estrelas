package com.empresa.smartestrelas.controller;

import com.empresa.smartestrelas.dto.PersonalRecordResponse;
import com.empresa.smartestrelas.dto.WorkoutSessionRequest;
import com.empresa.smartestrelas.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.empresa.smartestrelas.service.WorkoutSessionService;
@RestController
@RequestMapping("/workout-sessions")
public class WorkoutSessionController {
    private WorkoutSessionService service;

    // POST /workout-sessions - Registra uma sessão realizada
    @PostMapping
    public ResponseEntity<Void> registrar(
            @Valid @RequestBody WorkoutSessionRequest request,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        // Log de entrada
        System.out.println("INFO: Registrando sessão de treino - POST /workout-sessions - Usuário: " + usuarioLogado.getId());

        service.registrarSessao(request, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // GET /exercises/{id}/personal-record - Consulta o Recorde Pessoal
    @GetMapping("/exercises/{exerciseId}/personal-record")
    public ResponseEntity<PersonalRecordResponse> obterPR(
            @PathVariable Long exerciseId,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        // Log de entrada
        System.out.println("INFO: Consultando Recorde Pessoal - GET /personal-record - Exercício: " + exerciseId);

        PersonalRecordResponse pr = service.obterPR(exerciseId, usuarioLogado);
        return ResponseEntity.ok(pr);
    }
}

