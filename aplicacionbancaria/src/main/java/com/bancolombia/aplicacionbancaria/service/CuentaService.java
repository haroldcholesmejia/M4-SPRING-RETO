package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.config.CuentaInvalidaException;
import com.bancolombia.aplicacionbancaria.config.MontoInvalidoException;
import com.bancolombia.aplicacionbancaria.config.SaldoInsuficienteException;
import com.bancolombia.aplicacionbancaria.entity.Cuenta;
import com.bancolombia.aplicacionbancaria.entity.DTO.TransaccionDTO;
import com.bancolombia.aplicacionbancaria.entity.Transaccion;
import com.bancolombia.aplicacionbancaria.repository.CuentaRepository;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private final CuentaRepository cuentaRepository;

    @Autowired
    private final TransaccionRepository transaccionRepository;

    public CuentaService(CuentaRepository cuentaRepository, TransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }
    public void guardarTransaccion(Transaccion transaccion) {
        transaccionRepository.save(transaccion);
    }

    public BigDecimal consultarSaldo(Long numeroCuenta) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(numeroCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new CuentaInvalidaException();
        }
        return cuentaOpt.get().getSaldo();
    }


    @Transactional
    public void depositar(TransaccionDTO transaccionDTO) {
        BigDecimal monto = transaccionDTO.getMonto();
        Long idCuenta = transaccionDTO.getCuentaId();

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontoInvalidoException();
        }

        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(CuentaInvalidaException::new);

        if (transaccionDTO.getTipoTransaccion() == Transaccion.TipoTransaccion.RETIRO_CAJERO) {
            throw new RuntimeException( "No se puede depositar con este tipo de transacción");
        }
        BigDecimal comision = cuenta.calcularComisionDeposito(transaccionDTO.getTipoTransaccion());
        BigDecimal montoFinal = monto.subtract(comision);
        cuenta.setSaldo(cuenta.getSaldo().add(montoFinal));

        Transaccion transaccion = new Transaccion(transaccionDTO.getTipoTransaccion(), montoFinal, cuenta, LocalDateTime.now());
        cuenta.agregarTransaccion(transaccion);
        cuentaRepository.save(cuenta);
    }

    public List<Transaccion> obtenerUltimasTransacciones(Long idCuenta) {
        Cuenta cuenta = cuentaRepository.findById(idCuenta).orElseThrow();
        return cuenta.getListaTransacciones().stream()
                .limit(5)
                .toList();
    }

    @Transactional
    public void retirar(TransaccionDTO transaccionDTO) {
        BigDecimal monto = transaccionDTO.getMonto();
        Long idCuenta = transaccionDTO.getCuentaId();
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontoInvalidoException();
        }

        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(idCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new CuentaInvalidaException();
        }

        Cuenta cuenta = cuentaOpt.get();
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            throw new SaldoInsuficienteException();
        }

        if (transaccionDTO.getTipoTransaccion() != (Transaccion.TipoTransaccion.RETIRO_CAJERO)) {
            throw new RuntimeException("No se puede retirar con este tipo de transacción");
        }
        BigDecimal comision = cuenta.calcularComisionRetiro(transaccionDTO.getTipoTransaccion());
        BigDecimal montoFinal = monto.add(comision);

        if (cuenta.getSaldo().compareTo(montoFinal) < 0) {
            throw new SaldoInsuficienteException();
        }

        cuenta.setSaldo(cuenta.getSaldo().subtract(montoFinal));
        Transaccion transaccion = new Transaccion(transaccionDTO.getTipoTransaccion(), montoFinal, cuenta, LocalDateTime.now());
        cuenta.agregarTransaccion(transaccion);
        cuentaRepository.save(cuenta);
    }


    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaInvalidaException());
    }

    private void registrarTransaccion(Cuenta cuenta, BigDecimal monto, Transaccion.TipoTransaccion tipo) {
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(tipo);
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());
        cuenta.agregarTransaccion(transaccion);
        transaccionRepository.save(transaccion);
    }

    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }


}
