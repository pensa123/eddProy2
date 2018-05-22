/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.rest;

import edd.ip.edd.contenedor;
import edd.ip.model.Estacion;
import edd.ip.model.Pagos;
import edd.ip.model.Ticket;
import java.time.ZonedDateTime;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author pensa
 */
@Path("Ticket")
public class Tickets {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Tickets
     */
    public Tickets() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket addEst(String rt) {
        //contenedor.getInstance
        Ticket t = new Ticket();
        try {
            t = new ObjectMapper().readValue(rt, Ticket.class);
            boolean esDevolucion = false;
            if (t.verificacion != null) {
                esDevolucion = true;
                String tick = t.verificacion.split(",")[1];
                Ticket jk = contenedor.getInstance().arbolB.buscar(Integer.parseInt(tick));
                //Ticket jk = contenedor.getInstance().arbolB.buscarIngrid(t.verificacion);
                if (jk != null) {
                    t.valor = jk.valor;
                    jk.valor = 0;
                    jk.devolucion = ZonedDateTime.now().toString();
                } else {
                    t.valor = -1;
                }
            }
            if (t.valor != 0 && !esDevolucion) {
                contenedor.getInstance().insertarTicket(t);
                contenedor.getInstance().arbolB.ultimoCodigo = t.codigo;
            }
            //contenedor.getInstance().agregarEstacion(e);
            return t;
        } catch (Exception ee) {
            try {
                Pagos p = new ObjectMapper().readValue(rt, Pagos.class);
                Estacion auxe = contenedor.getInstance().getEstacion(p.estacion);
                Ticket tic = contenedor.getInstance().arbolB.buscar(p.codigo);
                if (tic.saldo - auxe.precio >= 0) {
                    p.est = auxe;
                    tic.saldo -= auxe.precio;
                }
                t.saldo = -1;
            } catch (Exception iii) {
                t.saldo = -2;
            }
        }
        return t;
    }

    /**
     * Retrieves representation of an instance of edd.ip.rest.Tickets
     *
     * @return an instance of edd.ip.model.Ticket
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket[] getJson() {
        //TODO return proper representation object
        return contenedor.getInstance().getTickets();
    }

    /**
     * PUT method for updating or creating an instance of Tickets
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Ticket content) {
    }
}
