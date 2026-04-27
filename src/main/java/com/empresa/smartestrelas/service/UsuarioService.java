package com.empresa.smartestrelas.service;

import com.empresa.smartestrelas.dto.UsuarioRequest;
import com.empresa.smartestrelas.dto.UsuarioResponse;
import com.empresa.smartestrelas.exception.EmailJaCadastradoException;
import com.empresa.smartestrelas.exception.SenhaInvalidaException;
import com.empresa.smartestrelas.model.Usuario;
import com.empresa.smartestrelas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException();
        }
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new SenhaInvalidaException();
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Usuario usuario = new Usuario();
        usuario.setUserName(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(hashedPassword);
        usuario.setTrainingLevel(request.getTrainingLevel());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getUserName(),
                usuario.getEmail(),
                usuario.getTrainingLevel(),
                usuario.getCreatedAt());
    }
}

