package com.agenda_service_back.usuarios;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Buscar todos os usuários
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        System.out.println(usuarios);
        return usuarioMapper.toDTOList(usuarios);
    }

    // Buscar um usuário pelo id
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        return usuarioMapper.toDTO(new UsuarioDTO());
    }

    // Criar um novo usuário
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioDTO);
    }

    // Atualizar usuário
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        // Atualizar o usuário com os dados do usuarioDTO
        System.out.println(usuario);
        Endereco endereco = usuario.getEndereco();
        usuario.setEndereco(endereco);
        System.out.println("dto " + usuarioDTO);
        usuario = usuarioMapper.updateEntity(usuarioDTO, usuario);
        usuario = usuarioRepository.save(usuario);
        usuarioDTO = usuarioMapper.toDTO(usuarioDTO);
        // Retornar o usuário entidade convertido em DTO
        return usuarioDTO;
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
