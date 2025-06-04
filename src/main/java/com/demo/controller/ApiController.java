package com.demo.controller;

import com.demo.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculadora")
public class ApiController {

    @Autowired
    private CalculadoraService calculadoraService;

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