package com.agenda_service_back.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT s FROM Usuario s WHERE s.usuario_email = :usuario_email")

    Usuario findByusuario_email(String usuario_email);
}
