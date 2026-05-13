package com.empresa.smartestrelas.service;

import com.empresa.smartestrelas.dto.PerformedExercicioRequest;
import com.empresa.smartestrelas.dto.PersonalRecordResponse;
import com.empresa.smartestrelas.dto.WorkoutSessionRequest;
import com.empresa.smartestrelas.model.*;
import com.empresa.smartestrelas.repository.TrainingPlanDayRepository;
import com.empresa.smartestrelas.repository.WorkoutSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class WorkoutSessionService {
    @Autowired
    private WorkoutSessionRepository repository;

    @Autowired
    private TrainingPlanDayRepository dayRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Transactional
    public void registrarSessao(WorkoutSessionRequest request, Usuario usuario) {
        WorkoutSession sessao = new WorkoutSession();
        sessao.setDate(request.date());
        sessao.setUsuario(usuario);

        // Busca o dia do plano para referência
        TrainingPlanDay dia = dayRepository.findById(request.trainingPlanDayId())
                .orElseThrow(() -> new RuntimeException("Dia do plano não encontrado"));
        sessao.setTrainingPlanDay(dia);

        // Mapeia os exercícios realizados e suas séries (Sets)
        for (PerformedExercicioRequest exReq : request.exercises()) {
            PerformedExercicios realizado = new PerformedExercicios();

            Exercicio exercicio = exercicioRepository.findById(exReq.exerciseId())
                    .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));

            realizado.setExercicio(exercicio);
            realizado.setWorkoutSession(sessao);

            // Converte os DTOs de Séries para a lista de registros reais
            realizado.setSets(exReq.sets().stream()
                    .map(s -> new PerformedSet(s.reps(), s.weightKg()))
                .collect(Collectors.toList()));

            sessao.getPerformedExercicios().add(realizado);
        }

        repository.save(sessao);
    }

    // Lógica para o Personal Record (PR)
    public PersonalRecordResponse obterPR(Long exerciseId, Usuario usuario) {
        // O repositório fará o trabalho pesado de buscar o maior peso
        return repository.findMaxWeightByExerciseAndUser(exerciseId, usuario.getId())
                .orElseThrow(() -> new RuntimeException("Nenhum registro encontrado para este exercício"));
    }
}
