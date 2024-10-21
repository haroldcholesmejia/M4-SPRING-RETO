package com.bancolombia.aplicacionbancaria.config;

public class MontoInvalidoException extends RuntimeException {
    public MontoInvalidoException() {
        super("El monto del depósito debe ser mayor a cero. Valide la informacion enviada");
    }
}

