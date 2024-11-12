package com.example.calculadoraarb.dinamicos;

public class Pila<E> {
    private Nodo<E> arriba;
    private int length;

    /**
     * Iniciliza la pila en vacia
     */
    public Pila() {
        this.arriba = null;
        this.length = 0;
    }

    /**
     * Retorna el elemento agregado recientemente
     * @return el elemento agregado recientemente
     */
    public Nodo verArriba() {
        return this.arriba;
    }

    /**
     * Agrega un elementos a la pila
     * @param valor valor que sera agregado
     * @return la pila con el nuevo elemento
     */
    public Pila<E> agregar(E valor) {
        Nodo<E> nuevoNodo = new Nodo<>(valor);
        if (length == 0) {
            arriba = nuevoNodo;
        } else {
            Nodo<E> sostenerPuntero = arriba;
            arriba = nuevoNodo;
            arriba.next = sostenerPuntero;
        }
        this.length++;
        return this;
    }

    /**
     * Retorna el numero de elementos en la pila
     * @return el numero de elementos en la pila
     */
    public int getLongitud() {
        return this.length;
    }

    /**
     * Imprime los elementos de la pila
     */
    public void imprimir() {
        Nodo<E> nodoActual = arriba;
        while (nodoActual != null) {
            System.out.print(nodoActual.valor + " -> ");
            nodoActual = nodoActual.next;
        }
        System.out.println("null");
    }

    /**
     * Retorna true si la pila es vacia
     * @return true si la pila es vacia; false de lo contrario
     */
    public boolean esVacia() {
        return arriba == null;
    }

    /**
     * Remueve y retorna el elemento agregado recientemente a la pila
     * @return el elemento agregado recientemente
     */
    public E pop() {
        if (length == 0) {
            return null;
        }
        E valor = arriba.valor;
        arriba = arriba.next;
        this.length--;
        return valor;
    }
}
