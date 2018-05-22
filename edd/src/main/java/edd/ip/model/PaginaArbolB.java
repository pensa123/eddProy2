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
public class PaginaArbolB {

    public int n = 0;
    public PaginaArbolB padre;
    public PaginaArbolB[] enlace;
    public Ticket[] contenido;
    public int ntickets, nenlaces;
    public int ultimoCodigo = 0;

    public PaginaArbolB(int n) {
        enlace = new PaginaArbolB[n];
        contenido = new Ticket[n - 1];
        ntickets = 0;
        nenlaces = 0;
        this.n = n;
    }

    public PaginaArbolB addTicket(Ticket t) {
        insertar(t);
        PaginaArbolB raiz = this;
        while (raiz.padre != null) {
            raiz = raiz.padre;
        }
        return raiz;
    }

    public int insertarP(Ticket t) {

        if (ntickets == 0) {
            contenido[0] = t;
            ntickets++;
            return 0;
        }
        if (t.codigo < contenido[0].codigo) {
            insertar(t, 0);
        }

        for (int a = 1; a < ntickets; a++) {
            if (contenido[a - 1].codigo < t.codigo && t.codigo < contenido[a].codigo) {

                //hay que insertar  y correr las cosas pero no va a llegar aqui 
                //por que como le vamos a insertar siempre acendente mente :) 
                return -1;
            }
        }

        if (ntickets == n - 1) {
            PaginaArbolB pabP = new PaginaArbolB(n);
            if (padre != null) {
                pabP = padre;
            } else {
                this.padre = pabP;
            }
            PaginaArbolB pabH = new PaginaArbolB(n);
            Ticket[] rM = new Ticket[n - 1];
            Ticket[] rH = new Ticket[n - 1];
            PaginaArbolB[] e1 = new PaginaArbolB[n];
            PaginaArbolB[] e2 = new PaginaArbolB[n];
            for (int a = 0; a < ntickets / 2; a++) {
                rH[a] = contenido[a];
            }
            int aux = 0;
            Ticket ts = contenido[ntickets / 2];
            for (int a = (ntickets / 2) + 1; a < ntickets; a++) {
                rM[aux] = contenido[a];
                aux++;
            }
            rM[aux] = t;
            this.contenido = rM;
            pabH.contenido = rH;
            int a = pabP.insertarP(ts);
            ntickets = aux + 1;
            if (pabP.nenlaces == 0) {
                pabP.nenlaces = 2;
            } else {
                pabP.nenlaces++;
            }
            pabP.enlace[a] = pabH;
            pabP.enlace[a + 1] = this;
            for (a = 0; a < (nenlaces + 1) / 2; a++) {
                e1[a] = enlace[a];
            }
            int c = 0;
            for (a = (nenlaces + 1) / 2; a < nenlaces; a++) {
                e2[c] = enlace[a];
                c++;
            }
            pabH.padre = pabP;
            pabH.enlace = e1;
            pabH.nenlaces = (nenlaces + 1) / 2;
            enlace = e2;
            nenlaces = c;
        } else {
            contenido[ntickets] = t;
            ntickets++;
        }
        return ntickets - 1;
    }

    public void insertar(Ticket t) {

        if (ntickets == 0) {
            contenido[0] = t;
            ntickets++;
            return;
        }
        if (t.codigo < contenido[0].codigo) {
            insertar(t, 0);
        }
        /*for (int a = 1; a < ntickets; a++) {
            if (contenido[a - 1].codigo < t.codigo && t.codigo < contenido[a].codigo) {

                //hay que insertar  y correr las cosas pero no va a llegar aqui 
                //por que como le vamos a insertar siempre acendente mente :) 
                return;
            }
        }*/
        if (nenlaces != 0) {
            enlace[nenlaces - 1].insertar(t);
            return;
        }
        if (ntickets == n - 1) {
            PaginaArbolB pabP = new PaginaArbolB(n);
            if (padre != null) {
                pabP = padre;
            } else {
                this.padre = pabP;
            }
            PaginaArbolB pabH = new PaginaArbolB(n);
            Ticket[] rM = new Ticket[n - 1];
            Ticket[] rH = new Ticket[n - 1];
            for (int a = 0; a < ntickets / 2; a++) {
                rH[a] = contenido[a];
            }
            int aux = 0;
            Ticket ts = contenido[ntickets / 2];
            for (int a = (ntickets / 2) + 1; a < ntickets; a++) {
                rM[aux] = contenido[a];
                aux++;
            }
            rM[aux] = t;
            aux++;
            this.contenido = rM;
            pabH.contenido = rH;
            int a = pabP.insertarP(ts);
            pabP.enlace[a] = pabH;
            pabP.enlace[a + 1] = this;
            pabH.ntickets = ntickets / 2;
            if (pabP.nenlaces == 0) {
                pabP.nenlaces = 2;
            } else {
                pabP.nenlaces++;
            }
            ntickets = aux;
        } else {
            contenido[ntickets] = t;
            ntickets++;
        }
    }

    public void insertar(Ticket t, int p) {
        if (enlace[p] == null) {
            enlace[p] = new PaginaArbolB(n);
            enlace[p].padre = this;
            nenlaces++;
        }
        enlace[p].insertar(t);
    }

    public Ticket buscar(int codigo) throws Exception {
        for (int a = 0; a < this.n - 1; a++) {
            if (contenido[a] == null) {
                return enlace[a].buscar(codigo);
            }
            if (contenido[a].codigo == codigo) {
                return contenido[a];
            }
            if (codigo < contenido[a].codigo) {
                return enlace[a].buscar(codigo);
            }
        }
        return enlace[n - 1].buscar(codigo);
    }

    public Ticket buscarIngrid(String verificacion) {
        for (int i = 1; i <= this.ultimoCodigo; i++) {
            try {
                Ticket aux = this.buscar(i);
                if (aux.verificacion.equals(verificacion)) {
                    return aux;
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

}
