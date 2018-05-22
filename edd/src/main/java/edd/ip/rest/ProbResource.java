/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.rest;

import edd.ip.edd.contenedor;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author pensa
 */
@Path("prob")
public class ProbResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProbResource
     */
    public ProbResource() {
    }

    /**
     * Retrieves representation of an instance of edd.ip.rest.ProbResource
     *
     * @return an instance of edd.ip.edd.contenedor
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public contenedor getJson() {
        //TODO return proper representation object
        String aux = contenedor.getInstance().toString();
        int b = 2; 
        
        return contenedor.getInstance();
    }

    /**
     * PUT method for updating or creating an instance of ProbResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(contenedor content) {
    }
}
