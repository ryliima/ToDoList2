package com.List.ToDo.dto;

import com.List.ToDo.entities.Status;
import com.List.ToDo.entities.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TarefaDto {

    private String nome;
    private String descricao;
    private Status status;
    private LocalDate dtInicio;
    private LocalDate dtFim;

    private Long idUser;

    public TarefaDto(Tarefa tarefa) {
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
        this.dtInicio = tarefa.getDtInicio();
        this.dtFim = tarefa.getDtFim();
        this.idUser = tarefa.getUsuario().getId();
    }
}