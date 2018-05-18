/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.model;

import edd.ip.edd.contenedor;
import java.time.ZonedDateTime;

/**
 *
 * @author pensa
 */
public class Ticket {

    public int codigo;
    public String verificacion, emision, devolucion;
    public double valor, saldo;

    public Ticket() {

    }

    public Ticket(String verificacion, double valor) {
        codigo = contenedor.getInstance().nticket;
        contenedor.getInstance().nticket++;
        this.verificacion = verificacion;
        this.emision = ZonedDateTime.now().toString();
        this.valor = valor;
        saldo = valor;
    }

}
