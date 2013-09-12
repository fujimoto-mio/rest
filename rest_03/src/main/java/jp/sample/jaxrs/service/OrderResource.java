package jp.sample.jaxrs.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/order")
public interface OrderResource {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Order create(Order order);

    @GET
    @Path("/get/{orderNo}")
    @Produces(MediaType.APPLICATION_JSON)
    Order getOrder(@PathParam("orderNo") Long orderNo);

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Order update(Order order);

    @DELETE
    @Path("/cancel/{orderNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    Order cancel(@PathParam("orderNo") Long orderNo);

    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    List<Order> history();
}
