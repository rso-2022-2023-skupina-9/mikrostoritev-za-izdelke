package si.fri.rso.skupina09.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.skupina09.lib.Izdelek;
import si.fri.rso.skupina09.services.beans.IzdelekBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/izdelki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE")
public class MikrostoritevZaIzdelkeResource {

    private Logger logger = Logger.getLogger(MikrostoritevZaIzdelkeResource.class.getName());

    @Inject
    private IzdelekBean izdelekBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Pridobi informacije o vseh izdelkih.", summary = "Pridobi vse izdelke")
    @APIResponses({
            @APIResponse(
                responseCode = "200",
                description = "Seznam vseh izdelkov",
                content = @Content(schema = @Schema(implementation = Izdelek.class, type = SchemaType.ARRAY)),
                headers = {@Header(name = "X-Total-Count", description = "Stevilo objektov v seznamu")}
            )
    })
    @GET
    public Response getIzdelek() {
        List<Izdelek> izdelki = izdelekBean.getIzdelek(uriInfo);
        return Response.status(Response.Status.OK).entity(izdelki).build();
    }

    @Operation(description = "Pridobi informacije o izdelku.", summary = "Pridobi izdelek")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Informacije o izdelku",
                    content = @Content(
                            schema = @Schema(implementation = Izdelek.class)
                    )
            )
    })
    @GET
    @Path("{izdelekId}")
    public Response getIzdelek(@Parameter(description = "Izdelek ID", required = true)
                               @PathParam("izdelekId") Integer izdelekId) {
        Izdelek izdelek = izdelekBean.getIzdelek(izdelekId);
        if(izdelek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(izdelek).build();
    }

    @Operation(description = "Dodaj izdelek.", summary = "Dodaj izdelek")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Izdelek uspesno dodan."
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Napaka pri preverjanju podatkov."
            )
    })
    @POST
    public Response createIzdelek(@RequestBody(
            description = "DTO objekt z informacijami o izdelku",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Izdelek.class)
            )
    ) Izdelek izdelek) {
        System.out.println(izdelek.getTrgovina_id());
        System.out.println(izdelek.getVrsta_id());
        System.out.println(izdelek.getIme());
        System.out.println(izdelek.getCena());
        if((izdelek.getTrgovina_id() == null) || (izdelek.getVrsta_id() == null) || (izdelek.getIme() == null) || (izdelek.getCena() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            izdelek = izdelekBean.createIzdelek(izdelek);
        }
        return Response.status(Response.Status.CREATED).entity(izdelek).build();
    }

    @Operation(description = "Posodobi informacije o izdelku.", summary = "Posodobi izdelek")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Izdelek uspesno posodobljen"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Izdelek ne obstaja"
            )
    })
    @PUT
    @Path("{izdelekId}")
    public Response putIzdelek(@Parameter(description = "Izdelek ID.", required = true)
                               @PathParam("izdelekId") Integer izdelekId,
                               @RequestBody(
                                       description = "DTO objekt z informacijami o izdelku.",
                                       required = true,
                                       content = @Content(
                                               schema = @Schema(implementation = Izdelek.class)
                                       )
                               ) Izdelek izdelek) {
        izdelek = izdelekBean.putIzdelek(izdelekId, izdelek);
        if(izdelek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(izdelek).build();
    }

    @Operation(description = "Izbrisi informacije o izdelku.", summary = "Izbrisi izdelek")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Izdelek uspesno izbrisan"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Izdelek ne obstaja"
            )
    })
    @DELETE
    @Path("{izdelekId}")
    public Response deleteIzdelek(@Parameter(description = "Izdelek ID.", required = true)
                                  @PathParam("izdelekId") Integer izdelekId) {
        boolean deleted = izdelekBean.deleteIzdelek(izdelekId);
        if(deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
