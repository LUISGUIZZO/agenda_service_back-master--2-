package com.agenda_service_back.usuarios;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Gera uma chave secreta segura para o HS256
    private static final String SECRET_KEY = "iFA£f3$ChLv:$O&+ul;£9y8O/d}$j9A8";
    private static final long EXPIRATION_TIME = 3600000; // 1 hora em milissegundos

    public String authenticate(String usuario_email, String usuario_senha) {
        Usuario usuario = usuarioRepository.findByusuario_email(usuario_email);
        if (usuario != null && usuario.getUsuario_senha().equals(usuario_senha)) {
            return Jwts.builder()
                    .setSubject(usuario.getUsuario_email())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())) // Corrigido para usar a chave diretamente
                    .compact();
        }
        return null;
    }
}