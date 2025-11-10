package com.intinalambrico.documentationfibra.web;


import com.intinalambrico.documentationfibra.domains.NapBox;
import com.intinalambrico.documentationfibra.domains.Port;
import com.intinalambrico.documentationfibra.services.NapBoxService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/nap-boxes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NapBoxResource {
    @Inject
    NapBoxService napBoxService;

    @GET
    public Response findAll() {
        List<NapBox> napBoxes = napBoxService.findAll();
        return Response.ok(napBoxes).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        return Response.ok(napBoxService.findById(id)).build();
    }


    @GET
    @Path("/type")
    public Response findByType(@QueryParam("type") String type , @QueryParam("idEstacion")String idEstacion) {
        List<NapBox> napBoxes = napBoxService.findByType(type , idEstacion);
        return Response.ok(napBoxes).build();
    }

    @GET
    @Path("/name/{nameNap}")
    public Response findByNameNap(@PathParam("nameNap") String nameNap) {
        return napBoxService.findByNameNap(nameNap)
                .map(napBox -> Response.ok(napBox).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/area")
    public Response findByArea(
            @QueryParam("minLat") Double minLat,
            @QueryParam("maxLat") Double maxLat,
            @QueryParam("minLng") Double minLng,
            @QueryParam("maxLng") Double maxLng) {

        if (minLat == null || maxLat == null || minLng == null || maxLng == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Todos los parámetros de área son requeridos")
                    .build();
        }

        List<NapBox> napBoxes = napBoxService.findByArea(minLat, maxLat, minLng, maxLng);
        return Response.ok(napBoxes).build();
    }

    @POST
    public Response create(@Valid NapBox napBox) {
        NapBox created = napBoxService.create(napBox);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/updated")
    public Response update(@Valid NapBox napBox) {
        return napBoxService.update(napBox)
                .map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = napBoxService.delete(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{napBoxId}/ports/{portNumber}")
    public Response updatePort(
            @PathParam("napBoxId") String napBoxId,
            @PathParam("portNumber") Integer portNumber,
            @Valid Port port) {

        return napBoxService.updatePort(napBoxId, portNumber, port)
                .map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/{id}/available-ports")
    public Response countAvailablePorts(@PathParam("id") String id) {
        long count = napBoxService.countAvailablePorts(id);
        return Response.ok(new PortCountResponse(count)).build();
    }

    // DTO para respuesta de conteo
    public static class PortCountResponse {
        public long availablePorts;

        public PortCountResponse(long availablePorts) {
            this.availablePorts = availablePorts;
        }
    }
}
