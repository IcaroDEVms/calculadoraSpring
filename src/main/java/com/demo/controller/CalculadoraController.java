package com.demo.controller;

import com.demo.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Marca a classe como um controlador Spring MVC
@Controller
public class CalculadoraController {

    // Injeta uma instância de CalculadoraService.
    @Autowired
    private CalculadoraService calculadoraService;

    // Mapeia requisições GET para a URL raiz ("/").
    @GetMapping("/")
    public String mostrarFormulario() { // Exibe o formulário inicial da calculadora.
        return "calculadora"; // Retorna o nome da view (ex: calculadora.html).
    }

    // Mapeia requisições POST para a URL "/calcular".
    @PostMapping("/calcular") 
    public String calcular(@RequestParam String expressao, Model model) {
        try {
            double resultado = calculadoraService.calcularExpressao(expressao);
            model.addAttribute("resultado", resultado);
            model.addAttribute("expressao", expressao);
            model.addAttribute("erro", null);
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("expressao", expressao);
        }
        return "resultado"; // Retorna a view de resultado (ex: resultado.html).
    }
}