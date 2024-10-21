package com.bancolombia.aplicacionbancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bancolombia.aplicacionbancaria.repository")
public class AplicacionbancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicacionbancariaApplication.class, args);
	}

}
