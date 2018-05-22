/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.model;

import edd.ip.edd.Listap;

/**
 *
 * @author pensa
 */
public class Estacion {

    public codigo c = new codigo();
    public String cv;
    public String codigo;
    public String nombre;
    public double precio;
    public double longitud;
    public double latitud;
    public Listap lt;
    public int nCaminos;

    public void copiar(Estacion e) {
        codigo = e.codigo;
        nombre = e.nombre;
        precio = e.precio;
        longitud = e.longitud;
        latitud = e.latitud;
        c.codigo = e.codigo;
    }

    public camino[] obtenerCaminos() {
        camino[] c = new camino[nCaminos];
        Listap aux = lt;
        int i = 0;
        while (aux != null) {
            c[i] = (camino) aux.contenido;
            i++;
            aux = aux.siguiente;
        }
        return c;
    }

    @Override
    public String toString() {
        return "{" + "\"codigo\":\"" + codigo + "\", \"nombre\": \"" + nombre + "\",\"precio\":" + precio
                + ",\"longitud\":" + longitud + ",\"latidut\":" + latitud + "}";
    }

    public Estacion(String cod, String nom, double pre, double lon, double lat) {
        codigo = cod;
        nombre = nom;
        precio = pre;
        longitud = lon;
        latitud = lat;
    }

    public Estacion() {

    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

}
