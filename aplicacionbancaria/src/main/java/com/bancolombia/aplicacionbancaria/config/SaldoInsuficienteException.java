package com.bancolombia.aplicacionbancaria.config;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente para realizar el retiro. Valide la informacion enviada");
    }
}
