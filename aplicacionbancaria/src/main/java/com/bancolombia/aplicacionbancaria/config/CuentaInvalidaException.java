package com.bancolombia.aplicacionbancaria.config;

public class CuentaInvalidaException extends RuntimeException {
    public CuentaInvalidaException() {
        super("La cuenta es Invalida o inexistente. Valide la informacion enviada");
    }
}

