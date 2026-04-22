package com.empresa.smartestrelas.usuario.exception;

public class EmailJaCadastradoException extends RuntimeException{
    public EmailJaCadastradoException() {
        super("E-mail já cadastrado");
    }
}
