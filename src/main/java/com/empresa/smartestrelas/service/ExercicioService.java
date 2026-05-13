package com.empresa.smartestrelas.service;

import com.empresa.smartestrelas.dto.EquipamentosResponse;
import com.empresa.smartestrelas.dto.ExercicioRequest;
import com.empresa.smartestrelas.dto.ExercicioResponse;
import com.empresa.smartestrelas.dto.MusculosResponse;
import com.empresa.smartestrelas.exception.CategoriaInvalidaException;
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

    @Transactional
    public ExercicioResponse criar(ExercicioRequest request) {
        List<String> categoriasValidas = List.of("Abs", "Arms", "Back", "Calves", "Cardio", "Chest", "Legs", "Shoulders");
        if (!categoriasValidas.contains(request.categoria())) {
            throw new CategoriaInvalidaException(); // Crie esta exceção e trate no GlobalExceptionHandler
        }
        System.out.println("INFO: Criando exercício - POST /exercicios - Usuário: ADMIN");

        Exercicio exercicio = new Exercicio();
        exercicio.setTitulo(request.titulo());
        exercicio.setDescricao(request.descricao());
        exercicio.setVideoUrl(request.videoUrl());
        exercicio.setCategoria(request.categoria());

        // 2. Busca as entidades completas no banco usando os IDs do DTO
        // Isso é necessário para preencher o objeto que será salvo
        exercicio.setEquipamentos(equipamentoRepository.findAllById(request.equipamentoIds()));
        exercicio.setPrimaryMusculos(musculosRepository.findAllById(request.primaryMusculosIds()));

        if (request.secondaryMusculosIds() != null) {
            exercicio.setSecondaryMusculos(musculosRepository.findAllById(request.secondaryMusculosIds()));
        }

        // 3. Salva no banco de dados
        Exercicio salvo = exercicioRepository.save(exercicio);

        // 4. Retorna o DTO de Resposta (o formato que você quer no Postman)
        return new ExercicioResponse(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getDescricao(),
                salvo.getVideoUrl(),
                salvo.getCategoria(),
                salvo.getEquipamentos().stream()
                        .map(e -> new EquipamentosResponse(e.getId(), e.getNome())).toList(),
                salvo.getPrimaryMusculos().stream()
                        .map(m -> new MusculosResponse(m.getId(), m.getNome(), m.getNomeEn())).toList(),
                salvo.getSecondaryMusculos().stream()
                        .map(m -> new MusculosResponse(m.getId(), m.getNome(), m.getNomeEn())).toList()
        );


    }
}

