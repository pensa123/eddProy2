/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.edd;

import com.google.gson.Gson;
import edd.ip.model.Estacion;
import edd.ip.model.PaginaArbolB;
import edd.ip.model.TablaHash;
import edd.ip.model.Ticket;
import edd.ip.model.camino;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 *
 * @author pensa
 */
public class contenedor {

    public static String json = "";
    public static String rutaDoc = "/home/pensa/Escritorio/";

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

    public void Guardar(String texto, String ruta) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta + "algo.txt");
            pw = new PrintWriter(fichero);
            pw.println(texto);

        } catch (Exception e) {

        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {

            }

        }
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
        while (lst != null) {
            if (((Estacion) lst.contenido).codigo.equals(cod)) {
                return (Estacion) lst.contenido;
            }
            lst = lst.siguiente;
        }
        return null;
    }

    public void quitarCamino(camino c, Listap p, camino aux) throws Exception {
        camino c2 = (camino) p.contenido;
        if (aux.comparar(c2)) {
            if (p.siguiente == null) {
                p.contenido = c;
                aux.rt.nCaminos--;
                return;
            } else {
                aux.estacion1.lt = p.siguiente;
            }
            aux.rt.nCaminos--;
            return;
        }
        do {
            c2 = (camino) p.siguiente.contenido;
            if (aux.comparar(c2)) {
                if (p.siguiente.siguiente == null) {
                    p.siguiente = null;
                } else {
                    p.siguiente = p.siguiente.siguiente;
                }
                aux.rt.nCaminos--;
                return;
            }
            p = p.siguiente;
        } while (p != null);
    }

    public boolean editarCamino(camino c) {
        String[] aa = c.cv.split(",");
        try {
            camino aux = new camino();
            aux.estacion1 = getEstacion(aa[0]);
            aux.estacion2 = getEstacion(aa[1]);
            aux.rt = thash.getRut(aa[2]);
            Listap p = aux.estacion1.lt;
            camino c2 = (camino) p.contenido;
            if (aux.comparar(c2)) {
                if (p.siguiente == null) {
                    p.contenido = c;
                    return true;
                } else {
                    aux.estacion1.lt = p.siguiente;
                }
                aux.estacion1.nCaminos--;
                return agregarCamino(c);
            }
            do {
                c2 = (camino) p.siguiente.contenido;
                if (aux.comparar(c2)) {
                    if (p.siguiente.siguiente == null) {
                        p.siguiente = null;
                    } else {
                        p.siguiente = p.siguiente.siguiente;
                    }
                    aux.estacion1.nCaminos--;
                    return agregarCamino(c);
                }
                p = p.siguiente;
            } while (p != null);

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean agregarCamino(camino c) {

        c.estacion1 = this.getEstacion(c.est1);
        c.estacion2 = this.getEstacion(c.est2);
        c.e1 = c.estacion1.c;
        c.e2 = c.estacion2.c;

        c.rt = thash.getRut(c.ruta);
        c.r = c.rt.c;
        Listap p = c.estacion1.lt;
        if (p.contenido == null) {
            p.contenido = c;
            c.estacion1.nCaminos++;
            thash.agregar(c);

            return true;
        }
        while (p.siguiente != null) {
            camino c2 = (camino) p.contenido;
            if (c.comparar(c2)) {
                return false;
            }
            p = p.siguiente;
        }
        Listap niu = new Listap();
        p.siguiente = niu;
        niu.contenido = c;
        thash.agregar(c);
        c.estacion1.nCaminos++;
        return true;
    }

    public boolean editarEstacion(Estacion est) {
        Listap lst = lestaciones;
        do {
            Estacion eaux = (Estacion) lst.contenido;
            if (eaux.codigo.equals(est.cv)) {
                ((Estacion) lst.contenido).copiar(est);
                est.c.codigo = est.codigo;
                return true;
            }
            lst = lst.siguiente;
        } while (lst != null);
        return false;
    }

    public boolean agregarEstacion(Estacion est) {

        est.lt = new Listap();
        if (lestaciones.contenido == null) {
            lestaciones.contenido = est;
            nestaciones++;
            est.c.codigo = est.codigo;
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
        est.c.codigo = est.codigo;
        nestaciones++;
        return true;
    }

    protected contenedor() {
        lestaciones = new Listap();
        this.thash = new TablaHash(13);
        arbolB = new PaginaArbolB(7);
    }

    public static contenedor getInstance() {

        Gson gson = new Gson();
        if (instance == null) {
            instance = new contenedor();

            try {
                //String a = new FileReader("D:\\file.json"); 
                instance = gson.fromJson("", contenedor.class);
            } catch (Exception e) {

            }
        }

        json = gson.toJson(instance);
        instance.Guardar(json, rutaDoc);
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
