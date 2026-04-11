package com.apicultura.exception;

public class ColmenaNotFoundException extends RuntimeException {
    public ColmenaNotFoundException(int numero) {
        super("Colmena #" + numero + " no encontrada");
    }
}