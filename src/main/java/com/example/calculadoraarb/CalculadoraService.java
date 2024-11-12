package com.example.calculadoraarb;

import com.example.calculadoraarb.dinamicos.Pila;

public class CalculadoraService {

    /**
     * Dada una expresión aritmetica - infija - la convierte a la notación postfija
     *
     * @param expresionInfija expresion infija dada por el usuario
     * @return la expresion convertida en postfija
     * */
    public static String infijaAPostfija(String expresionInfija) {
        Pila<Character> pila = new Pila<>();
        StringBuilder postfix = new StringBuilder();

        for (char caracter : expresionInfija.toCharArray()) {
            if (Character.isDigit(caracter)) {
                postfix.append(caracter);
            } else if (caracter == '(') {
                pila.agregar(caracter);
            } else if (caracter == ')') {
                while (!pila.esVacia() && (char)(pila.verArriba().valor) != '(') {
                    postfix.append(pila.pop());
                }
                pila.pop();
            } else {
                while (!pila.esVacia() && precedence(caracter) <= precedence( (char) pila.verArriba().valor)) {
                    postfix.append(pila.pop());
                }
                pila.agregar(caracter);
            }
        }
        while (!pila.esVacia()) {
            postfix.append(pila.pop());
        }
        return postfix.toString();
    }

    /**
     * Determina la jerarquia del operador
     *
     * @param caracter operador
     * @return 2 para operadores de precedencia mayor, 1 para precedencia menor y menos sino se encuentra en la lista
     * */
    private static int precedence(char caracter) {
        switch (caracter) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
}
