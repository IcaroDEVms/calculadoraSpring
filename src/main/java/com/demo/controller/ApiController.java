package com.demo.controller;

import com.demo.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Anotação que marca esta classe como um controlador REST.
@RequestMapping("/api/calculadora") // Define o caminho base para todos os endpoints (URLs) gerenciados por este controlador.
public class ApiController {

    @Autowired // Anotação para injeção de dependência.
    // Declara uma variável para armazenar a instância do serviço da calculadora.
    private CalculadoraService calculadoraService; 

    // Mapeia requisições HTTP do tipo POST para o caminho "/api/calculadora/calcular".
    @PostMapping("/calcular") 
    public ResponseEntity<?> calcular(@RequestBody String expressao) {
        try {
            double resultado = calculadoraService.calcularExpressao(expressao);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}