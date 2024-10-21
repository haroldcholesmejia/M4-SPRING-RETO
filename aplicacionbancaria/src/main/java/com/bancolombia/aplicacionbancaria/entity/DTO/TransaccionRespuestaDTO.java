package com.bancolombia.aplicacionbancaria.entity.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionRespuestaDTO {
    private Long id;
    private String tipoTransaccion;
    private BigDecimal monto;
    private LocalDateTime fecha;

    public TransaccionRespuestaDTO(Long id, String tipoTransaccion, BigDecimal monto, LocalDateTime fecha) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
    public void setTipoTransaccion(String tipoTransaccion) {
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
}
