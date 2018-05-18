/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.model;

/**
 *
 * @author pensa
 */
public class NodoHash {
     NodoHash siguiente;
    ruta ruta;

    public void insertar(ruta codigo) {
        if (ruta == null) {
            ruta = codigo;
        } else {
            System.out.println("choque ");
            NodoHash n = new NodoHash();
            n.insertar(codigo);
            siguiente = n;
        }
    }

    public ruta get(String st) {
        if (ruta.codigo.equals(st)) {
            return ruta;
        }

        if (this.siguiente != null) {
            return this.siguiente.get(st);
        }

        return null;
    }
    
    
}
