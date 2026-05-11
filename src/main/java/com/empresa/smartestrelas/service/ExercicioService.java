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
    private ExercicioRepository repository;

    public List<Exercicio> listarTodos() {
        return repository.findAll();
    }

    // Buscar por tratamento de erro (Retorna 404 se não existir)
    public Exercicio buscarPorId(Long id) {
        return repository.findById(id)
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
        return repository.save(exercicio);
    }
    public List<Exercicio> listarComFiltros(String categoria, Long equipamentoId, Long musculoId) {
        if (categoria != null) return repository.findByCategory(categoria);
        if (equipamentoId != null) return repository.findByEquipment_Id(equipamentoId);
        if (musculoId != null) return repository.findByPrimaryMuscles_Id(musculoId);

        return repository.findAll();
    }
    @Transactional
    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        Exercicio exercicioExistente = buscarPorId(id);

        exercicioExistente.setTitulo(exercicioAtualizado.getTitulo());
        exercicioExistente.setDescricao(exercicioAtualizado.getDescricao());
        exercicioExistente.setCategoria(exercicioAtualizado.getCategoria());

        return repository.save(exercicioExistente);
    }
    @Transactional
    public void excluir(Long id) {
        Exercicio exercicio = buscarPorId(id);
        repository.delete(exercicio);
    }
}
