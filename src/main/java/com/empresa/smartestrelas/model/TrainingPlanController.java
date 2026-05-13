package com.empresa.smartestrelas.model;

import com.empresa.smartestrelas.dto.TrainingPlanRequest;
import com.empresa.smartestrelas.dto.TrainingPlanResponse;
import com.empresa.smartestrelas.service.TrainingPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training-plans")
public class TrainingPlanController {
    @Autowired
    private TrainingPlanService service;

    @PostMapping
    public ResponseEntity<TrainingPlanResponse> criar(
            @Valid @RequestBody TrainingPlanRequest request,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        // Log de entrada exigido pelo Sprint (INFO)
        System.out.println("INFO: Criando plano de treino - POST /training-plans - Usuário: " + usuarioLogado.getEmail());

        TrainingPlanResponse response = service.criar(request, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TrainingPlanResponse>> listar(@AuthenticationPrincipal Usuario usuarioLogado) {
        // Log de entrada (INFO)
        System.out.println("INFO: Listando planos de treino - GET /training-plans - Usuário: " + usuarioLogado.getEmail());

        List<TrainingPlanResponse> planos = service.listarPorUsuario(usuarioLogado);
        return ResponseEntity.ok(planos);
    }
}
