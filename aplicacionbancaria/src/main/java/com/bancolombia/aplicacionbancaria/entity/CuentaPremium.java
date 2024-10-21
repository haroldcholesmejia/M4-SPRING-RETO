package com.bancolombia.aplicacionbancaria.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CuentaPremium extends Cuenta {

    public CuentaPremium() {
        super();
    }

    public CuentaPremium(String nombreTitular, String Celular, String direccion, String email, BigDecimal saldo, String numeroCuenta) {
        super(nombreTitular, Celular, direccion, email, saldo, numeroCuenta);
    }

    @Override
    public BigDecimal calcularComisionDeposito(Transaccion.TipoTransaccion tipoTransaccion) {
        switch (tipoTransaccion) {
            case DEPOSITO_OTRA_CUENTA:
                return BigDecimal.valueOf(1.5);
            case COMPRA_PAGINA_WEB:
                return BigDecimal.valueOf(5);
            default:
                return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calcularComisionRetiro(Transaccion.TipoTransaccion tipoTransaccion) {
        return BigDecimal.ZERO;
    }


}
