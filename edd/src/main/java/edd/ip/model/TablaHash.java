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

    public boolean agregar(ruta codigo) {
        int n = dohash(codigo.codigo);

        if (nl[n] == null) {
            nl[n] = new NodoHash();
        }
        contenedor.getInstance().nruta++;
        System.out.println(n + "   " + codigo.codigo);
        nl[n].insertar(codigo);
        return true;
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
