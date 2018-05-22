/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.rest;

import edd.ip.model.Estacion;
import edd.ip.model.ruta;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edd.ip.edd.contenedor;
import edd.ip.edd.Listap;
import edd.ip.model.camino;
import javax.ws.rs.PathParam;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author pensa
 */
@Path("estaciones")
public class Estaciones {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Estaciones() {
    }

    /**
     * Retrieves representation of an instance of edd.ip.rest.GenericResource
     *
     * @return an instance of edd.ip.model.Estacion
     */
    @GET
    @Path("/{param}")
    public camino[] getRut(@PathParam("param") String message) {
        message = message.split(",")[0];
        return contenedor.getInstance().getEstacion(message).obtenerCaminos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Estacion[] getJson() {
        Estacion[] el = new Estacion[contenedor.getInstance().nestaciones];
        int a = 0;
        Listap l = contenedor.getInstance().lestaciones;
        while (l != null) {
            if (l.contenido == null) {
                break;
            }
            Estacion e = (Estacion) l.contenido;
            el[a] = e;
            a++;
            l = l.siguiente;
        }
        return el;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Estacion content) {

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Estacion addEst(String rt) {
        //contenedor.getInstance
        int a = 1;
        Estacion e;
        try {
            e = new ObjectMapper().readValue(rt, Estacion.class);
            if (e.cv == null) {
                if (contenedor.getInstance().agregarEstacion(e)) {
                    return e;
                }
            } else {
                String au = contenedor.json;
                if (contenedor.getInstance().editarEstacion(e)) {
                    return e;
                }
            }
        } catch (Exception ee) {
        }
        return new Estacion();
    }

}
