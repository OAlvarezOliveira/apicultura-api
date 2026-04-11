package com.apicultura.exception;

public class ColmenaYaExisteException extends RuntimeException {
    public ColmenaYaExisteException(int numero) {
        super("La colmena #" + numero + " ya existe");
    }
}