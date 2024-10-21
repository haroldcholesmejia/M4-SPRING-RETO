package com.bancolombia.aplicacionbancaria.controller;
import com.bancolombia.aplicacionbancaria.entity.DTO.CuentaDTO;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/saldo")
    public ResponseEntity<String> obtenerSaldo(@RequestParam Long numeroCuenta) {
        BigDecimal saldo = cuentaService.consultarSaldo(numeroCuenta);
        CuentaDTO cuentaDTO = new CuentaDTO(numeroCuenta, saldo, "Número de cuenta"); // Ajusta el constructor según lo necesites
        return new ResponseEntity<>("El saldo de la cuenta es: " + saldo, HttpStatus.OK);
    }


}
