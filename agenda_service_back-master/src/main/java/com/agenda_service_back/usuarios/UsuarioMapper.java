package com.agenda_service_back.usuarios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "usuario_id", target = "usuario_id")
    UsuarioDTO toDTO(UsuarioDTO usuario);

    @Mapping(source = "usuarioDTO.usuario_id", target = "usuario_id")
    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDTOList(List<Usuario> usuarios);


    @Mappings({
            @Mapping(source = "usuarioDTO.usuario_id", target = "usuario_id"),
            @Mapping(source = "usuarioDTO.usuario_cpf", target = "usuario_cpf"),
            @Mapping(source = "usuarioDTO.usuario_email", target = "usuario_email"),
            @Mapping(source = "usuarioDTO.usuario_data_nascimento", target = "usuario_data_nascimento"),
            @Mapping(source = "usuarioDTO.usuario_senha", target = "usuario_senha"),
            @Mapping(source = "usuarioDTO.endereco", target = "endereco"),
            @Mapping(source = "usuarioDTO.telefones", target = "telefones"),
            @Mapping(source = "usuarioDTO.agendamentos", target = "agendamentos")

    })
    Usuario updateEntity(UsuarioDTO usuarioDTO, Usuario usuario);
}
