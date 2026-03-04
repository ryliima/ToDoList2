package com.List.ToDo.service;

import com.List.ToDo.dto.TarefaDto;
import com.List.ToDo.entities.Tarefa;
import com.List.ToDo.entities.Usuario;
import com.List.ToDo.repositories.TarefaRepository;
import com.List.ToDo.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public TarefaService(UsuarioRepository usuarioRepository,
                        TarefaRepository tarefaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criarTarefa(Long usuarioId, TarefaDto dto){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Tarefa tarefa = new Tarefa();
        tarefa.setUsuario(usuario);
        tarefa.setNome(dto.getNome());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDtFim(dto.getDtFim());

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasPorUsuario(Long usuarioId){

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        return tarefaRepository.findByUsuarioId(usuarioId);
    }

    public Tarefa atualizarTarefa(Long id, TarefaDto dto){

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        tarefa.setNome(dto.getNome());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDtFim(dto.getDtFim());

        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id){

        if (!tarefaRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada");
        }

        tarefaRepository.deleteById(id);
    }
}