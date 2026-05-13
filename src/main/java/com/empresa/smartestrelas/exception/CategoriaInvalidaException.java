package com.empresa.smartestrelas.exception;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException() {
        super("Categoria inválida. Use: Abs, Arms, Back, Calves, Cardio, Chest, Legs ou Shoulders.");
    }
}
