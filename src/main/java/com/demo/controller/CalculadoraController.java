package com.demo.controller;

import com.demo.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    @GetMapping("/")
    public String mostrarFormulario() {
        return "calculadora";
    }

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
        return "resultado";
    }
}