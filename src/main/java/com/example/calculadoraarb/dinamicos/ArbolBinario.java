package com.example.calculadoraarb.dinamicos;

// ExpressionTree
public class ArbolBinario<E> {
    public NodoA<E> raiz;


    private boolean existeOperador(String caracter) {
        return caracter.trim().equals("/") || caracter.trim().equals("*") || caracter.trim().equals("+") || caracter.trim().equals("-");
    }

    /**
     * Pasa de la notación postfija a un arbol binario de expresiones
     * @param cadenaPost la expresion del usuario en notacion postfija
     * @return el nodo raiz del arbol
     * */
    public NodoA<E> arbolI(String cadenaPost) {
        Pila<NodoA<E>> pila = new Pila<>();

        for (char caracter : cadenaPost.toCharArray()) {
            if (Character.isDigit(caracter)) {
                pila.agregar(new NodoA(caracter));
            } else {
                NodoA node = new NodoA(caracter);
                node.derecha = pila.pop();
                node.izquierda = pila.pop();
                pila.agregar(node);
            }
        }
        return pila.pop();
    }

    /**
     * Metodo recursivo que evalua el arbol en postorden para calcula el resultado de la expresion
     *
     * @param nodo raiz del arbol binario de expresiones
     * @return resultado del arbol binario de expresiones
     * */
    public double evaluar(NodoA<E> nodo) {
        if (nodo == null) return 0;

        if (nodo.izquierda == null && nodo.derecha == null) {
            return Character.getNumericValue((Character) nodo.valor);
        }

        double valorIzquierda = evaluar(nodo.izquierda);
        double valorDerecha = evaluar(nodo.derecha);

       switch ((Character) nodo.valor) {
                case '+': return valorIzquierda + valorDerecha;
                case '-': return valorIzquierda - valorDerecha;
                case '*': return valorIzquierda * valorDerecha;
                case '/': return valorIzquierda / valorDerecha;
            }
        return 0;
    }

    /**
     * Recorre el arbol binario para imprimir los valores de cada nodo en el orden correspondiente
     *
     * @param nodo raiz del arbol binario de expresiones
     * */
    public void postOrden(NodoA<E> nodo) {
        if (nodo != null) {
            postOrden(nodo.izquierda);
            postOrden(nodo.derecha);
            System.out.print(nodo.valor + " ");
        }
    }
}
