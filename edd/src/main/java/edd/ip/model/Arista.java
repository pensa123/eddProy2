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
public class Arista {
    private NodoGrafo origen;
    private NodoGrafo destino;
    private double distancia;
    private double trafico;
    private double peso;

    public Arista(NodoGrafo origen, NodoGrafo destino, double distancia, double trafico) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.trafico = trafico;
    }

    public NodoGrafo getOrigen() {
        return origen;
    }

    public void setOrigen(NodoGrafo origen) {
        this.origen = origen;
    }

    public NodoGrafo getDestino() {
        return destino;
    }

    public void setDestino(NodoGrafo destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTrafico() {
        return trafico;
    }

    public void setTrafico(double trafico) {
        this.trafico = trafico;
    }
    
}