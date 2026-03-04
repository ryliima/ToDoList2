package com.List.ToDo.controller;

import com.List.ToDo.dto.TarefaDto;
import com.List.ToDo.entities.Tarefa;
import com.List.ToDo.service.TarefaService;
import com.List.ToDo.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping("/usuarios/{id}/tarefas")
    public ResponseEntity<TarefaDto> criarTarefa(
            @PathVariable Long id,
            @Valid @RequestBody TarefaDto dto) {

        Tarefa tarefa = tarefaService.criarTarefa(id, dto);
        TarefaDto tarefaResponse = new TarefaDto(tarefa);
        return ResponseEntity.status(201).body(tarefaResponse);
    }


    @GetMapping("/usuarios/{id}/tarefas")
    public ResponseEntity<List<Tarefa>> listarTarefasPorUsuario(
            @PathVariable Long id) {

        List<Tarefa> tarefas = tarefaService.listarTarefasPorUsuario(id);
        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(
            @PathVariable Long id,
            @RequestBody TarefaDto dto) {

        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, dto);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {

        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}