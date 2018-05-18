/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.edd;

import edd.ip.model.Estacion;
import edd.ip.model.PaginaArbolB;
import edd.ip.model.TablaHash;
import edd.ip.model.Ticket;

/**
 *
 * @author pensa
 */
public class contenedor {

    public void insertarTicket(Ticket t) {
        arbolB = arbolB.addTicket(t);
    }

    public PaginaArbolB arbolB;
    public TablaHash thash;
    public int nticket = 0, nruta = 0;
    private static contenedor instance = null;
    public lista lestaciones;
    public int nestaciones = 0;

    public void agregarEstacion(Estacion est) {

        if (lestaciones.contenido == null) {
            lestaciones.contenido = est;
            nestaciones++;
            return;
        }
        lista lst = lestaciones;

        while (lst.siguiente != null) {
            lst = lst.siguiente;
        }
        lista lst2 = new lista();
        lst2.contenido = est;
        lst.siguiente = lst2;
        nestaciones++;
    }

    protected contenedor() {
        lestaciones = new lista();
        this.thash = new TablaHash(13);
    }

    public static contenedor getInstance() {
        if (instance == null) {
            instance = new contenedor();
        }
        return instance;
    }

    Ticket[] art;
    int aux;

    public Ticket[] imp(PaginaArbolB b) {
        art = new Ticket[17];
        aux = 0;
        recorrer(b);

        return art;
    }

    public void recorrer(PaginaArbolB b) {
        if (b == null) {
            return;
        }
        for (int a = 0; a < b.nenlaces; a++) {
            recorrer(b.enlace[a]);
            try {
                System.out.println(aux + " " + b.contenido[a].codigo + " " + b.contenido[a].verificacion);
                art[aux] = b.contenido[a];
                aux++;
            } catch (Exception e) {
                b.ntickets = a;
            }
        }
        if (b.nenlaces == 0) {
            for (int a = 0; a < b.n - 1; a++) {
                try {
                    System.out.println(aux + " " + b.contenido[a].codigo + " " + b.contenido[a].verificacion);
                    art[aux] = b.contenido[a];
                    aux++;
                } catch (Exception e) {
                    b.ntickets = a;
                }
            }
        }
    }

}
