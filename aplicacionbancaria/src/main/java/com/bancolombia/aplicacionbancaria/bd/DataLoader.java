package com.bancolombia.aplicacionbancaria.bd;

import com.bancolombia.aplicacionbancaria.entity.CuentaBasica;
import com.bancolombia.aplicacionbancaria.entity.CuentaPremium;
import com.bancolombia.aplicacionbancaria.repository.CuentaRepository;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CuentaRepository cuentaRepository;
    private TransaccionRepository transaccionRepository;

    @Override
    public void run(String... args) throws Exception {
        CuentaBasica cuentaBasica1 = new CuentaBasica("Luis Maria", "311521410", "calle 12", "email@prueba.com", new BigDecimal("150000"), "12345");
        CuentaPremium cuentaPremium1 = new CuentaPremium("Maria Lopez", "354178", "calle 12", "email@prueba.com",new BigDecimal("2500000"), "54321");
        CuentaBasica cuentaBasica2 = new CuentaBasica("Sophia Choles", "8541236", "calle 12", "email@prueba.com",new BigDecimal("350000"), "852963");
        CuentaPremium cuentaPremium2 = new CuentaPremium("Melissia Orozco", "7412586", "calle 12", "email@prueba.com",new BigDecimal("600000"), "369258");
        CuentaBasica cuentaBasica3 = new CuentaBasica("Karen margarita", "85632", "calle 12", "email@prueba.com",new BigDecimal("870000"), "741852");
        CuentaPremium cuentaPremium3 = new CuentaPremium("Daniel Lopez", "74133", "calle 12", "email@prueba.com",new BigDecimal("1000"), "251487");


        cuentaRepository.saveAll(Arrays.asList(cuentaBasica1, cuentaPremium1));
        cuentaRepository.saveAll(Arrays.asList(cuentaBasica2, cuentaPremium2));
        cuentaRepository.saveAll(Arrays.asList(cuentaBasica3, cuentaPremium3));

    }
}
