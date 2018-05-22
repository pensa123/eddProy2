/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.model;

/**
 *
 * @author ingri
 */
public class Lista<T> {

    private NodoLista<T> cabeza;
    private NodoLista<T> cola;

    public Lista() {
        this.cabeza = null;
        this.cola = null;
    }

    public NodoLista<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoLista<T> cabeza) {
        this.cabeza = cabeza;
    }

    public NodoLista<T> getCola() {
        return cola;
    }

    public void setCola(NodoLista<T> cola) {
        this.cola = cola;
    }

    public void insertar(T info) {

        NodoLista<T> nuevo = new NodoLista<>(info);
        if (this.getCabeza() == null) {
            this.setCabeza(nuevo);
        } else {
            this.cola.setSiguiente(nuevo);
            nuevo.setAnterior(this.getCola());
        }
        this.setCola(nuevo);
    }
}
