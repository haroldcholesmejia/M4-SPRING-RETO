package com.bancolombia.aplicacionbancaria.entity;

import com.bancolombia.aplicacionbancaria.entity.Cuenta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;

    @NotNull(message = "El monto de la transacción es obligatoria")
    @Positive(message = "El monto debe ser mayor a cero!")
    @Min(value = 10000, message = "El monto mínimo a depositar/retiro debe ser de 10.000")
    @Max(value = 1000000, message = "El monto máximo aceptable a depositar/retiro debe ser hasta 1.000.000")
    private BigDecimal monto;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    @JsonBackReference
    private Cuenta cuenta;

    public Transaccion() { }

     public Transaccion(TipoTransaccion tipoTransaccion, BigDecimal monto, Cuenta cuenta, LocalDateTime fecha) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.cuenta = cuenta;
        this.fecha = LocalDateTime.now();
    }

    public enum TipoTransaccion {
        DEPOSITO_SUCURSAL,
        DEPOSITO_CAJERO,
        DEPOSITO_OTRA_CUENTA,
        COMPRA_ESTABLECIMIENTO_FISICO,
        COMPRA_PAGINA_WEB,
        RETIRO_CAJERO,

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }
    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Cuenta getCuenta() {
        return cuenta;
    }
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
