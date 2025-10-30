package br.com.fiap.resource;

import br.com.fiap.bo.ItensVendidosBO;
import br.com.fiap.to.ItensVendidosTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/itensvendidos")
public class ItensVendidosResource {
    private ItensVendidosBO itensBO = new ItensVendidosBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<ItensVendidosTO> resultado = itensBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codVenda}/{codRemedio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByKeys(@PathParam("codVenda") Long codVenda, @PathParam("codRemedio") Long codRemedio){
        ItensVendidosTO resultado = itensBO.findByKeys(codVenda, codRemedio);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ItensVendidosTO item) {
        ItensVendidosTO resultado = itensBO.save(item);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();

    }

    @DELETE
    @Path("/{codVenda}/{codRemedio}")
    public Response delete(@PathParam("codVenda") Long codVenda, @PathParam("codRemedio") Long codRemedio) {
        Response.ResponseBuilder response = null;
        if (itensBO.delete(codVenda, codRemedio)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{codVenda}/{codRemedio}")
    public Response update( @Valid ItensVendidosTO item, @PathParam("codVenda") Long codVenda, @PathParam("codRemedio") Long codRemedio) {
        item.setCodVenda(codVenda);
        item.setCodRemedio(codRemedio);
        ItensVendidosTO resultado = itensBO.update(item);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }
}

