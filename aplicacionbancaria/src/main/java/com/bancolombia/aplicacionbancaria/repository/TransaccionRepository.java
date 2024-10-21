package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findTop5ByCuentaIdOrderByFechaDesc(Long cuentaId);
}
