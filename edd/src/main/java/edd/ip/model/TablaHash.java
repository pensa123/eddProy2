/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.model;

import edd.ip.edd.contenedor;

/**
 *
 * @author pensa
 */
public class TablaHash {

    NodoHash[] nl;
    int largo = 0;

    public TablaHash(int n) {
        largo = n;
        nl = new NodoHash[n];
    }

    public ruta getRut(String st) {
        int a = dohash(st);

        if (nl[a] == null) {
            return null;
        }
        return nl[a].get(st);
    }

    public boolean editar(ruta r) {
        boolean bl = false;
        if (r.cv.equals(r.codigo)) {
            ruta ar = this.getRut(r.codigo);
            ar.nombre = r.nombre;
            ar.color = r.color;
            return true;
        } else {
            int n = dohash(r.cv);
            NodoHash nodo = nl[n];
            if (nodo.ruta.codigo.equals(r.cv)) {
                nl[n] = nl[n].siguiente;
                bl = true;
            } else {
                while (nodo.siguiente != null) {
                    if (nodo.siguiente.ruta.codigo.equals(r.cv)) {
                        nodo.siguiente = nodo.siguiente.siguiente;
                        bl = true;
                        break;
                    }
                    nodo = nodo.siguiente;
                }
            }
            if (bl) {

                this.agregar(r);
                contenedor.getInstance().nruta = contenedor.getInstance().nruta - 1;
                return true;
            }
        }
        return false;
    }

    public ruta[] getRutas() {
        int n = contenedor.getInstance().nruta;
        ruta[] rtlist = new ruta[n];
        int aux = 0;
        for (NodoHash item : nl) {
            while (item != null) {
                rtlist[aux] = item.ruta;
                aux++;
                item = item.siguiente;
            }
        }
        return rtlist;

    }

    public boolean agregar(camino c) {
        return false;
    }

    public boolean editar(camino c) {
        return false;
    }

    public boolean agregar(ruta codigo) {
        int n = dohash(codigo.codigo);

        if (nl[n] == null) {
            nl[n] = new NodoHash();
        }
        boolean ab = nl[n].insertar(codigo);
        if (ab) {
            contenedor.getInstance().nruta++;
            System.out.println(n + "   " + codigo.codigo);
        }
        return ab;
    }

    public int dohash(String codigo) {
        int aux = 0;

        char[] ch = codigo.toCharArray();

        for (int a = 0; a < ch.length; a++) {
            aux += (int) ch[a] % largo;
        }
        aux = aux % largo + codigo.length() % largo;
        aux = aux % largo;

        return aux;
    }

    public int rehash(String codigo) {
        int aux = 0;

        return aux;
    }

}
