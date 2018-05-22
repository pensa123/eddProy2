/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ingri
 */
public class Grafo {

    private String codigo;
    private String nombre;
    private String color;
    private Lista<Arista> recorrido;

    public Grafo(String codigo, String nombre, String color) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
        this.recorrido = new Lista<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Lista<Arista> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Lista<Arista> recorrido) {
        this.recorrido = recorrido;
    }

    public boolean insertarArista(NodoGrafo origen, NodoGrafo destino, double distancia, double trafico) {
        if (origen == null || destino == null) {
            return false;
        }
        Arista nueva = new Arista(origen, destino, distancia, trafico);
        getRecorrido().insertar(nueva);
        return true;
    }

    public boolean modificarRecorrido(NodoGrafo origenA, NodoGrafo destinoA, NodoGrafo nuevoOrigen, NodoGrafo nuevoDestino, double nuevaDistancia, double nuevoTrafico) {
        for (NodoLista a = this.recorrido.getCabeza(); a != null; a = a.getSiguiente()) {
            Arista ar = (Arista) a.getInformacion();
            if (ar.getOrigen() == origenA && ar.getDestino() == destinoA) {

            }
        }
        return false;
    }

    public String getRuta() {
        String graph = "";
        for (NodoLista a = this.recorrido.getCabeza(); a != null; a = a.getSiguiente()) {
            Arista ar = (Arista) a.getInformacion();
            graph += ar.getOrigen().getNombre() + "->" + ar.getDestino().getNombre() + "\n";
        }
        return graph;
    }
}
