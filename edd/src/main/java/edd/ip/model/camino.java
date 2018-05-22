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
public class camino {

    public codigo e1;
    public codigo e2;
    public codigo r;
    public String est1;
    public Estacion estacion1;
    public String est2;
    public String ruta;
    public ruta rt;
    public Estacion estacion2;
    public double distancia;
    public double trafico;
    public String cv;

    public boolean comparar(camino c) {
        return (estacion1.codigo.equals(c.estacion1.codigo)
                && estacion2.codigo.equals(c.estacion2.codigo)
                && rt.codigo.equals(c.rt.codigo));
    }

    /**
     * @return the est1
     */
    public String getEst1() {
        return est1;
    }

    /**
     * @param est1 the est1 to set
     */
    public void setEst1(String est1) {
        this.est1 = est1;
    }

    /**
     * @return the estacion1
     */
    public Estacion getEstacion1() {
        return estacion1;
    }

    /**
     * @param estacion1 the estacion1 to set
     */
    public void setEstacion1(Estacion estacion1) {
        this.estacion1 = estacion1;
    }

    /**
     * @return the est2
     */
    public String getEst2() {
        return est2;
    }

    /**
     * @param est2 the est2 to set
     */
    public void setEst2(String est2) {
        this.est2 = est2;
    }

    /**
     * @return the estacion2
     */
    public Estacion getEstacion2() {
        return estacion2;
    }

    /**
     * @param estacion2 the estacion2 to set
     */
    public void setEstacion2(Estacion estacion2) {
        this.estacion2 = estacion2;
    }

    /**
     * @return the distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the cv
     */
    public String getCv() {
        return cv;
    }

    /**
     * @param cv the cv to set
     */
    public void setCv(String cv) {
        this.cv = cv;
    }
}
