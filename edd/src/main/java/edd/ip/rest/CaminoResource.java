/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.ip.rest;

import edd.ip.edd.contenedor;
import edd.ip.model.camino;
import edd.ip.model.ruta;
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
@Path("camino")
public class CaminoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CaminoResource
     */
    public CaminoResource() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public camino addRuta(String rt) {
        camino tur;
        try {
            tur = new ObjectMapper().readValue(rt, camino.class);
            if (tur.getCv() == null) {

                contenedor.getInstance().agregarCamino(tur);
            } else {
                contenedor.getInstance().editarCamino(tur);
            }
            tur.estacion1 = null;
            tur.estacion2 = null;
            tur.rt = null;
            return tur;
        } catch (Exception ioe) {
            tur = new camino();
            tur.est1 = "r1";
            tur.est2 = "r2";
            tur.ruta = "ruta";
            tur.estacion1 = null;
            tur.estacion2 = null;
            tur.rt = null;
            return tur;
        }
    }

    /**
     * Retrieves representation of an instance of edd.ip.rest.CaminoResource
     *
     * @return an instance of edd.ip.model.camino
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public camino[] getJson() {
        //TODO return proper representation object
        camino[] cp = new camino[2];
        cp[0] = new camino();
        cp[1] = new camino();

        return cp;
    }

    /**
     * PUT method for updating or creating an instance of CaminoResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(camino content) {
    }
}
