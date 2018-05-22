/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.rest;

import edd.ip.edd.contenedor;
import edd.ip.model.camino;
import edd.ip.model.ruta;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author pensa
 */
@Path("rutas")
public class rutas {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of rutas
     */
    public rutas() {
    }

    /**
     * Retrieves representation of an instance of edd.ip.rest.rutas
     *
     * @return an instance of edd.ip.model.ruta
     */
    @GET
    @Path("/{param}")
    public camino[] getRut(@PathParam("param") String message) {
        String output = "busca la ruta " + message + "!";
        output += "j";
        output = "";
        return contenedor.getInstance().thash.getcaminos(message.split(",")[0] + output);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ruta addRuta(String rt) {
        ruta tur;
        try {
            tur = new ObjectMapper().readValue(rt, ruta.class);
            if (tur.cv == null) {
                contenedor.getInstance().thash.agregar(tur);
            } else {
                contenedor.getInstance().thash.editar(tur);
            }
        } catch (Exception ioe) {
            return null;
        }
        return tur;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ruta[] getJson() {
        //TODO return proper representation object

        int c = 2;
        ruta[] rt = contenedor.getInstance().thash.getRutas();
        c = 3;
        return rt;
    }

    /**
     * PUT method for updating or creating an instance of rutas
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(ruta content) {
    }
}
