package com.empresa.smartestrelas.exception;

public class EmailJaCadastradoException extends RuntimeException{
    public EmailJaCadastradoException() {
        super("E-mail já cadastrado");
    }
}
