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
import edd.ip.model.camino;
import java.time.ZonedDateTime;

/**
 *
 * @author pensa
 */
public class contenedor {

    public void insertarTicket(Ticket t) {
        nticket++;
        t.codigo = nticket;
        String aux = "";
        for (int a = 0; a < 5; a++) {
            int r2 = (int) (Math.random() * (122 - 97 + 1) + 97);
            System.out.println((char) r2);
            aux += (char) r2;
        }
        aux += "," + nticket;
        t.verificacion = aux;
        t.emision = ZonedDateTime.now().toString();
        arbolB = arbolB.addTicket(t);
    }

    public PaginaArbolB arbolB;
    public TablaHash thash;
    public int nticket = 0, nruta = 0;
    private static contenedor instance = null;
    public Listap lestaciones;
    public int nestaciones = 0;

    public Estacion getEstacion(String cod) {
        if (lestaciones.contenido == null) {
            return null;
        }
        Listap lst = lestaciones;
        if (((Estacion) lst.contenido).codigo.equals(cod)) {
            return (Estacion) lst.contenido;
        }
        while (lst.siguiente != null) {
            if (((Estacion) lst.contenido).codigo.equals(cod)) {
                return (Estacion) lst.contenido;
            }
            lst = lst.siguiente;
        }
        return null;
    }

    public boolean agregarCamino(camino c) {
        c.estacion1 = this.getEstacion(c.est1);
        c.estacion2 = this.getEstacion(c.est2);
        c.estacion1.lt.insertar(c, c.est1);
        thash.agregar(c);
        return false;
    }

    public boolean editarEstacion(Estacion est) {
        Listap lst = lestaciones;
        do {
            Estacion eaux = (Estacion) lst.contenido;
            if (eaux.codigo.equals(est.cv)) {
                ((Estacion) lst.contenido).copiar(est);
                return true;
            }
            lst = lst.siguiente;
        } while (lst != null);
        return false;
    }

    public boolean agregarEstacion(Estacion est) {

        if (lestaciones.contenido == null) {
            lestaciones.contenido = est;
            nestaciones++;
            return true;
        }
        Listap lst = lestaciones;
        if (((Estacion) lst.contenido).codigo.equals(est.codigo)) {
            return false;
        }
        while (lst.siguiente != null) {
            if (((Estacion) lst.contenido).codigo.equals(est.codigo)) {
                return false;
            }
            lst = lst.siguiente;
        }
        Listap lst2 = new Listap();
        lst2.contenido = est;
        lst.siguiente = lst2;
        nestaciones++;
        return true;
    }

    protected contenedor() {
        lestaciones = new Listap();
        this.thash = new TablaHash(13);
        arbolB = new PaginaArbolB(7);
    }

    public static contenedor getInstance() {
        if (instance == null) {
            instance = new contenedor();
        }
        return instance;
    }

    Ticket[] art;
    int aux;

    public Ticket[] getTickets() {
        return imp(arbolB);
    }

    public Ticket[] imp(PaginaArbolB b) {
        art = new Ticket[nticket];
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
