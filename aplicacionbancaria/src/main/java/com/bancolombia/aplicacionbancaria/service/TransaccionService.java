package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.entity.Transaccion;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> obtenerUltimasTransacciones(Long cuentaId) {
        return transaccionRepository.findTop5ByCuentaIdOrderByFechaDesc(cuentaId);
    }
}

