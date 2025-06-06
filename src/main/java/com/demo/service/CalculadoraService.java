package com.demo.service;

import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Service
public class CalculadoraService {
    
    public double calcularExpressao(String expressao) throws Exception {
        Queue<String> fila = new LinkedList<>();
        Stack<Double> pilha = new Stack<>();
        
        String[] elementos = expressao.split(" ");
        for (String elemento : elementos) {
            if (!elemento.trim().isEmpty()) {
                fila.add(elemento);
            }
        }
        
        while (!fila.isEmpty()) {
            String elemento = fila.poll();
            
            try {
                if (isNumero(elemento)) {
                    pilha.push(Double.parseDouble(elemento));
                } 
                else if (isOperador(elemento)) {
                    if (pilha.size() < 2) {
                        throw new Exception("Operandos insuficientes para o operador '" + elemento + "'");
                    }
                    
                    double b = pilha.pop();
                    double a = pilha.pop();
                    double resultado = realizarOperacao(a, b, elemento);
                    pilha.push(resultado);
                } 
                else {
                    throw new Exception("Elemento inválido '" + elemento + "'");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Número inválido '" + elemento + "'");
            }
        }
        
        if (pilha.size() != 1) {
            throw new Exception("Expressão malformada");
        }
        
        return pilha.pop();
    }
    
    private boolean isNumero(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    
    private boolean isOperador(String str) {
        return str.matches("[+\\-*/%]");
    }
    
    private double realizarOperacao(double a, double b, String operador) throws Exception {
        switch (operador) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new Exception("Divisão por zero");
                return a / b;
            case "%": return a % b;
            default: throw new Exception("Operador inválido");
        }
    }
}