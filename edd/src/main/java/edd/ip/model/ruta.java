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
public class ruta {

    public String codigo;
    public String nombre;
    public String color;

    public ruta() {

    }

    public ruta(String codigo, String nombre, String valor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = valor;
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
     * @return the valor
     */
    public String getColor() {
        return color;
    }

    /**
     * @param valor the valor to set
     */
    public void setColor(String valor) {
        this.color = valor;
    }
}
