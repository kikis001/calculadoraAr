package com.example.calculadoraarb.dinamicos;


// expresionNodo
public class NodoA<E> {
    public E valor;
    public NodoA<E> derecha, izquierda;

    public NodoA(E valor) {
        this.valor = valor;
        derecha = izquierda = null;
    }

    public Object obtenerValor() {
        return valor;
    }


}
