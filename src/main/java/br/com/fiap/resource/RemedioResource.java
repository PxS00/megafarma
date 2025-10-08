package br.com.fiap.resource;

import br.com.fiap.bo.RemedioBO;
import br.com.fiap.to.RemedioTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/megafarma")
public class RemedioResource {
    private RemedioBO  remedioBO = new RemedioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<RemedioTO> resultado = remedioBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 OK
        } else {
            response = Response.status(404); // 404 Not Found
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(RemedioTO remedio) {
        RemedioTO resultado = remedioBO.save(remedio);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 CREATED
        } else {
            response = Response.status(404); // 404 Not Found
        }
        response.entity(resultado);
        return response.build();

    }
}
