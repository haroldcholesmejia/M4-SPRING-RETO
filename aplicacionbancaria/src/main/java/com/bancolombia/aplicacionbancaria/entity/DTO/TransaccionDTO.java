package com.bancolombia.aplicacionbancaria.entity.DTO;

import com.bancolombia.aplicacionbancaria.entity.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionDTO {
    private Long cuentaId;
    private Transaccion.TipoTransaccion tipoTransaccion;
    private BigDecimal monto;
    private LocalDateTime fecha;

   public TransaccionDTO() {  }

    public TransaccionDTO(Transaccion.TipoTransaccion tipoTransaccion, BigDecimal monto, LocalDateTime fecha) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Long getCuentaId() {
        return cuentaId;
    }
    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }
    public Transaccion.TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }
    public void setTipoTransaccion(Transaccion.TipoTransaccion tipoTransaccion) {
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