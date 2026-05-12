package com.empresa.smartestrelas.service;

import com.empresa.smartestrelas.dto.ExercicioRequest;
import com.empresa.smartestrelas.model.Exercicio;
import com.empresa.smartestrelas.repository.EquipamentoRepository;
import com.empresa.smartestrelas.repository.ExercicioRepository;
import com.empresa.smartestrelas.repository.MusculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExercicioService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private MusculosRepository musculosRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    // Buscar por tratamento de erro (Retorna 404 se não existir)
    public Exercicio buscarPorId(Long id) {
        return exercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado com ID: " + id));
    }
    @Transactional
    public Exercicio salvarComIds(ExercicioRequest dto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setTitulo(dto.titulo());
        exercicio.setDescricao(dto.descricao());
        exercicio.setVideoUrl(dto.videoUrl());
        exercicio.setCategoria(dto.categoria());

        // Busca os objetos reais no banco usando os DTO
        exercicio.setEquipamentos(equipamentoRepository.findAllById(dto.equipamentoIds()));
        exercicio.setPrimaryMusculos(musculosRepository.findAllById(dto.primaryMusculosIds()));

        if (dto.secondaryMusculosIds() != null) {
            exercicio.setSecondaryMusculos(musculosRepository.findAllById(dto.secondaryMusculosIds()));
        }
        return exercicioRepository.save(exercicio);
    }
    public List<Exercicio> listarComFiltros(String categoria, Long equipamentoId, Long musculoId) {
        if (categoria != null) return exercicioRepository.findByCategoria(categoria);
        if (equipamentoId != null) return exercicioRepository.findByEquipamentos_Id(equipamentoId);
        if (musculoId != null) return exercicioRepository.findByPrimaryMusculos_Id(musculoId);

        return exercicioRepository.findAll();
    }
    @Transactional
    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        Exercicio exercicioExistente = buscarPorId(id);

        exercicioExistente.setTitulo(exercicioAtualizado.getTitulo());
        exercicioExistente.setDescricao(exercicioAtualizado.getDescricao());
        exercicioExistente.setCategoria(exercicioAtualizado.getCategoria());

        return exercicioRepository.save(exercicioExistente);
    }
    @Transactional
    public void excluir(Long id) {
        Exercicio exercicio = buscarPorId(id);
        exercicioRepository.delete(exercicio);
    }
}
