package com.empresa.smartestrelas.usuario;

import com.empresa.smartestrelas.usuario.exception.EmailJaCadastradoException;
import com.empresa.smartestrelas.usuario.exception.SenhaInvalidaException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    //pega uma senha normal e a transforma em um hash irreconhecível
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Usuario registrar(UsuarioRequest request) throws Exception {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException();
        }
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new SenhaInvalidaException();
        }
        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        return usuarioRepository.save(usuario);
    }
}

