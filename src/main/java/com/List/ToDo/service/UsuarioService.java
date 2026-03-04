
package com.List.ToDo.service;

import com.List.ToDo.dto.UsuarioDto;
import com.List.ToDo.entities.Usuario;
import com.List.ToDo.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto criarUsuario(UsuarioDto dto){

        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha()
        );

        usuario = usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }

    public List<UsuarioDto> listarUsuario() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDto::new)
                .collect(Collectors.toList());
    }

    public UsuarioDto buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        return new UsuarioDto(usuario);
    }

    public void deletar(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario não encontrado");
        }

        usuarioRepository.deleteById(id);
    }

    public UsuarioDto atualizar(Long id, UsuarioDto dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        usuario = usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }
}
