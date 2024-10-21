package com.bancolombia.aplicacionbancaria.controller;
import com.bancolombia.aplicacionbancaria.entity.DTO.TransaccionDTO;
import com.bancolombia.aplicacionbancaria.entity.DTO.TransaccionRespuestaDTO;
import com.bancolombia.aplicacionbancaria.entity.Transaccion;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import com.bancolombia.aplicacionbancaria.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/deposito")
    public ResponseEntity<String> depositar(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        System.out.println(transaccionDTO.toString());
        cuentaService.depositar(transaccionDTO);
        return ResponseEntity.ok("Depósito realizado con éxito");
    }

    @PostMapping("/retiro")
    public ResponseEntity<String> retirar(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        System.out.println(transaccionDTO.toString());
        cuentaService.retirar(transaccionDTO);
        return new ResponseEntity<>("Retiro realizado con exitoso", HttpStatus.OK);
    }

    @GetMapping("/ultimas/{cuentaId}")
    public ResponseEntity<List<TransaccionRespuestaDTO>> obtenerUltimasTransacciones(@PathVariable Long cuentaId) {
        List<Transaccion> transacciones = transaccionService.obtenerUltimasTransacciones(cuentaId);

        if (transacciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<TransaccionRespuestaDTO> respuestaDTOs = transacciones.stream()
                .map(t -> new TransaccionRespuestaDTO(
                        t.getId(),
                        t.getTipoTransaccion().name(),
                        t.getMonto(),
                        t.getFecha()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuestaDTOs);
    }



}
