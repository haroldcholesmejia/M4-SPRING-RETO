package com.bancolombia.aplicacionbancaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignorar propiedades internas de JPA
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreTitular;
    private String Celular;
    private String direccion;
    private String email;
    private BigDecimal saldo;
    private String numeroCuenta;

    @JsonIgnore
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaccion> listaTransacciones = new ArrayList<>();

    public Cuenta() { }

    public Cuenta(String nombreTitular, String Celular, String direccion, String email, BigDecimal saldo, String numeroCuenta) {
        this.nombreTitular = nombreTitular;
        this.Celular = Celular;
        this.direccion = direccion;
        this.email = email;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        this.listaTransacciones.add(transaccion);
        transaccion.setCuenta(this);
    }


    public abstract BigDecimal calcularComisionDeposito(Transaccion.TipoTransaccion tipoTransaccion);
    public abstract BigDecimal calcularComisionRetiro(Transaccion.TipoTransaccion tipoTransaccion);

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
    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }
    public void setListaTransacciones(List<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public String getCelular() {
        return Celular;
    }
    public void setCelular(String Celular) {
        this.Celular = Celular;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
