package com.List.ToDo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dtInicio;

    private LocalDate dtFim;

    // ✅ Relacionamento 1:N com Usuario
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy evita carregar usuário automaticamente
    @JoinColumn(name = "usuario_id", nullable = false) // chave estrangeira obrigatória
    private Usuario usuario;
}