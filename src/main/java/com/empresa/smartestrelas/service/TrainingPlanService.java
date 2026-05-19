package com.empresa.smartestrelas.service;

import com.empresa.smartestrelas.dto.*;
import com.empresa.smartestrelas.mapper.TrainingPlanMapper;
import com.empresa.smartestrelas.model.*;
import com.empresa.smartestrelas.repository.ExercicioRepository;
import com.empresa.smartestrelas.repository.TrainingPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlanService {

    @Autowired
    private TrainingPlanRepository repository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TrainingPlanMapper mapper;

    public List<TrainingPlanResponse> listarPlanos(Usuario usuario) {
        return repository.findByUsuario(usuario)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }
    public Page<TrainingPlanResponse> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::converterParaResponse); // Usa o conversor completo que mapeia as semanas!
    }

    @Transactional
    public TrainingPlanResponse criar(TrainingPlanRequest request, Usuario usuarioAutenticado) {
        TrainingPlan plano = mapper.toEntity(request);
        plano.setUsuario(usuarioAutenticado);

        // 3. Resolve os IDs dos exercícios vinculados a cada slot antes de salvar
        vincularExerciciosDoBanco(plano);

        // 4. Salva a árvore completa e converte para Response
        TrainingPlan salvo = repository.save(plano);
        return converterParaResponse(salvo);
    }
    private void vincularExerciciosDoBanco(TrainingPlan plano) {
        if (plano.getWeeks() == null) return;

        plano.getWeeks().stream()
                .flatMap(week -> week.getDias().stream())
                .flatMap(day -> day.getExercicios().stream())
                .forEach(slot -> {
                    // Busca a referência real do exercício para não dar erro de persistência transiente
                    Exercicio exercicioReal = exercicioRepository.findById(slot.getExercicio().getId())
                            .orElseThrow(() -> new RuntimeException("Exercício não encontrado: " + slot.getExercicio().getId()));
                    slot.setExercicio(exercicioReal);
                });
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
                week.getDias().stream().map(this::mapDay).toList()
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
                slot.getExercicio().getId(),
                slot.getExercicio().getTitulo(),
                slot.getSets(),
                slot.getReps(),
                slot.getWeightKg(),
                slot.getRestSeconds()
        );
    }
}