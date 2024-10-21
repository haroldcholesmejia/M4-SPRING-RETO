package com.bancolombia.aplicacionbancaria.entity.DTO;

import java.math.BigDecimal;

public class CuentaDTO {
    private Long id;
    private BigDecimal saldo;
    private String numeroCuenta;

    public CuentaDTO(Long id, BigDecimal saldo, String numeroCuenta) {
        this.id = id;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}