/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.rest;

import edd.ip.edd.contenedor;
import edd.ip.model.Estacion;
import edd.ip.model.Ticket;
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
        Ticket t;
        try {
            t = new ObjectMapper().readValue(rt, Ticket.class);
            contenedor.getInstance().insertarTicket(t);
            //contenedor.getInstance().agregarEstacion(e);
            return t;
        } catch (Exception ee) {
        }
        return null;
    }

    /**
     * Retrieves representation of an instance of edd.ip.rest.Tickets
     *
     * @return an instance of edd.ip.model.Ticket
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
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
