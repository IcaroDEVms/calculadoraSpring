package com.demo.service;

import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Service // Marca a classe como um serviço Spring
public class CalculadoraService {
    
    // Método principal para calcular uma expressão em notação pós-ordem.
    // Lança Exception em caso de erro.
    public double calcularExpressao(String expressao) throws Exception {
        // Inicializa uma fila para os elementos da expressão e uma pilha para os operandos e resultados.
        Queue<String> fila = new LinkedList<>();
        Stack<Double> pilha = new Stack<>();
        
        // Quebra a expressão em elementos e os adiciona à fila.
        String[] elementos = expressao.split(" ");
        for (String elemento : elementos) {
            if (!elemento.trim().isEmpty()) {
                fila.add(elemento);
            }
        }
        
        // Processa os elementos da fila sequencialmente.
        while (!fila.isEmpty()) {
            String elemento = fila.poll();
            
            try {
                if (isNumero(elemento)) { // Se for um número, converte e empilha.
                    pilha.push(Double.parseDouble(elemento));
                } 
                else if (isOperador(elemento)) { // Se for um operador:
                    // Verifica se há operandos suficientes na pilha.
                    if (pilha.size() < 2) {
                        throw new Exception("Operandos insuficientes para o operador '" + elemento + "'");
                    }
                    
                    // Desempilha os dois últimos operandos.
                    double b = pilha.pop();
                    double a = pilha.pop();
                    // Realiza a operação e empilha o resultado.
                    double resultado = realizarOperacao(a, b, elemento);
                    pilha.push(resultado);
                } 
                else {// Lança exceção caso haja um elemento inválido.
                    throw new Exception("Elemento inválido '" + elemento + "'");
                }
            } catch (NumberFormatException e) { // Trata erro de conversão para número.
                throw new Exception("Número inválido '" + elemento + "'");
            }
        }
        
        // Garante que a pilha tenha apenas um elemento (o resultado).
        if (pilha.size() != 1) {
            throw new Exception("Expressão malformada");
        }
        
        return pilha.pop(); // Retorna o resultado.
    }
    
    // Verifica se a string é um número (inteiro ou decimal).
    private boolean isNumero(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    
    // Verifica se a string é um operador válido (+, -, *, /, %).
    private boolean isOperador(String str) {
        return str.matches("[+\\-*/%]");
    }
    
    // Realiza a operação matemática entre dois números.
    private double realizarOperacao(double a, double b, String operador) throws Exception {
        switch (operador) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                // Verifica se há uma divisão por zero e lança uma exceção
                if (b == 0) throw new Exception("Divisão por zero");
                return a / b;
            case "%": return a % b;
            // Garante que uma exceceção seja disparada caso o operador seja considerado invalido.
            default: throw new Exception("Operador inválido");
        }
    }
}