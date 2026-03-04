package com.List.ToDo.dto;

import com.List.ToDo.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @NotBlank(message = "Digite um nome válido")
    private String nome;

    @NotBlank(message = "Digite um email válido")
    private String email;

    @Size(min = 6, max = 20, message = "Digite uma senha válido")
    private String senha;


    public UsuarioDto(Usuario user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
    }
}