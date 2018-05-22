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
public class NodoLista<T> {
    private T informacion;
    private NodoLista<T> anterior;
    private NodoLista<T> siguiente;

    public NodoLista(T informacion) {
        this.informacion = informacion;
        this.anterior = null;
        this.siguiente = null;
    }

    public T getInformacion() {
        return informacion;
    }

    public void setInformacion(T informacion) {
        this.informacion = informacion;
    }

    public NodoLista<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLista<T> anterior) {
        this.anterior = anterior;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }
     
    
}