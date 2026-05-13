package com.empresa.smartestrelas.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<String> handleEmailJaCadastrado(EmailJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        //Retorna o erro 409 (Conflict) quando você tenta criar algo que já existe
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<String> handleSenhaInvalida(SenhaInvalidaException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        //Retorna erro 400 (Bad Request) quando tenta inserir uma senha inválida
    }
    @ExceptionHandler(CategoriaInvalidaException.class)
    public ResponseEntity<StandardError> handleCategoriaInvalida(CategoriaInvalidaException ex, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}

