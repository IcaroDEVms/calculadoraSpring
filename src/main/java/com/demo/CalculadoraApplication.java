package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marca esta classe como a principal do Spring Boot, configurando-a para iniciar automaticamente.
@SpringBootApplication
public class CalculadoraApplication {

	public static void main(String[] args) {
		// Inicia a aplicação Spring Boot, executando as configurações e inicializando o contexto Spring.
		SpringApplication.run(CalculadoraApplication.class, args);
	}

}
