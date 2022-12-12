package si.fri.rso.skupina09.api.v1.resources;


import si.fri.rso.skupina09.services.config.ConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/broken")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrokenResource {

    private Logger logger = Logger.getLogger(BrokenResource.class.getName());

    @Inject
    private ConfigProperties configProperties;

    @GET
    @Path("break")
    public Response makeHealthy() {
        configProperties.setBroken(false);
        return Response.status(Response.Status.OK).entity("{\"message\": \"Set Custom Health Check to Healthy\"}").build();
    }

    @POST
    @Path("break")
    public Response makeUnhealthy() {
        configProperties.setBroken(true);
        return Response.status(Response.Status.OK).entity("{\"message\": \"Set Custom Health Check to Unhealthy\"}").build();
    }
}
