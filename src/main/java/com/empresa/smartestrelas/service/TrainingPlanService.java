package com.empresa.smartestrelas.service;

import com.empresa.smartestrelas.dto.*;
import com.empresa.smartestrelas.model.*;
import com.empresa.smartestrelas.repository.TrainingPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.smartestrelas.dto.ExerciciosSlotRequest;

import java.util.List;

@Service
public class TrainingPlanService {
    @Autowired
    private TrainingPlanRepository repository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<TrainingPlanResponse> listarPlanos(Usuario usuario) {
        // Usa o repositório para buscar apenas os planos do dono
        return repository.findByUsuario(usuario)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Transactional
    public TrainingPlanResponse criar(TrainingPlanRequest request, Usuario usuarioAutenticado) {
        // 1. Criar a Entidade Principal
        TrainingPlan plano = new TrainingPlan();
        plano.setName(request.name()); // [cite: 160]
        plano.setGoal(request.goal()); // [cite: 161]
        plano.setWeekCount(request.weekCount()); // [cite: 162]
        plano.setUsuario(usuarioAutenticado);


        // 2. Mapear Semanas
        for (TrainingPlanWeekRequest weekDto : request.weeks()) { // [cite: 163]
            TrainingPlanWeek week = new TrainingPlanWeek();
            week.setWeekNumber(weekDto.weekNumber());
            week.setTrainingPlan(plano);

            // 3. Mapear Dias
            for (TrainingPlanDayRequest dayDto : weekDto.days()) {
                TrainingPlanDay day = new TrainingPlanDay();
                day.setDayOfWeek(dayDto.dayOfWeek()); // [cite: 164]
                day.setSplitFocus(dayDto.splitFocus()); // [cite: 165]
                day.setWeek(week);

                // 4. Mapear Slots de Exercícios
                for (ExercicioSlot slotDto : dayDto.exercicios()) {
                    ExercicioSlot slot = new ExercicioSlot(); // Nome da sua Entidade

                    Exercicio exercicio = exercicioRepository.findById(slotDto.exerciciosId())
                            .orElseThrow(() -> new RuntimeException("Exercício não encontrado: " + slotDto.exerciciosId()));

                    slot.setExercicio(exercicio);
                    slot.setSets(slotDto.sets());
                    slot.setReps(slotDto.reps());
                    slot.setWeightKg(slotDto.weightKg());
                    slot.setRestSeconds(slotDto.restSeconds());
                    slot.setDay(day);

                    day.getExercicios().add(slot);
                }
                week.getDays().add(day);
            }
            plano.getWeeks().add(week);
        }

        TrainingPlan salvo = repository.save(plano);
        return converterParaResponse(salvo);
    }

    public TrainingPlanResponse converterParaResponse(TrainingPlan plano) {
        return new TrainingPlanResponse(
                plano.getId(),
                plano.getName(),
                plano.getGoal(),
                plano.getWeekCount(),
                plano.getWeeks().stream().map(this::mapWeek).toList()
        );
    }

    private TrainingPlanWeekResponse mapWeek(TrainingPlanWeek week) {
        return new TrainingPlanWeekResponse(
                week.getId(),
                week.getWeekNumber(),
                week.getDays().stream().map(this::mapDay).toList()
        );
    }

    private TrainingPlanDayResponse mapDay(TrainingPlanDay day) {
        return new TrainingPlanDayResponse(
                day.getId(),
                day.getDayOfWeek(),
                day.getSplitFocus(),
                day.getExercicios().stream().map(this::mapSlot).toList()
        );
    }

    private ExercicioSlotResponse mapSlot(ExercicioSlot slot) {
        return new ExercicioSlotResponse(
                slot.getId(),
                slot.getExercicios().getId(),
                slot.getExercicios().getTitulo(), // [cite: 70, 101]
                slot.getSets(),
                slot.getReps(),
                slot.getWeightKg(),
                slot.getRestSeconds()
        );
    }
}