package si.fri.rso.skupina09.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.skupina09.lib.Vrsta;
import si.fri.rso.skupina09.services.beans.VrstaBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/vrste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE")
public class MikrostoritevZaVrsteResource {

    private Logger logger = Logger.getLogger(MikrostoritevZaVrsteResource.class.getName());

    @Inject
    private VrstaBean vrstaBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Pridobi informacije o vseh vrstah izdelkov.", summary = "Pridobi vse vrste izdelkov")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Seznam vseh vrst izdelkov",
                    content = @Content(schema = @Schema(implementation = Vrsta.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo objektov v seznamu")}
            )
    })
    @GET
    public Response getVrsta() {
        List<Vrsta> vrste = vrstaBean.getVrsta(uriInfo);
        return Response.status(Response.Status.OK).entity(vrste).build();
    }

    @Operation(description = "Pridobi informacije o vrsti izdelka.", summary = "Pridobi vrsto izdelka")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Informacije o vrsti izdelka",
                    content = @Content(
                            schema = @Schema(implementation = Vrsta.class)
                    )
            )
    })
    @GET
    @Path("{vrstaId}")
    public Response getVrsta(@Parameter(description = "Vrsta ID", required = true)
                               @PathParam("vrstaId") Integer vrstaId) {
        Vrsta vrsta = vrstaBean.getVrsta(vrstaId);
        if(vrsta == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(vrsta).build();
    }

    @Operation(description = "Dodaj vrsto izdelka.", summary = "Dodaj vrsto izdelka")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Vrsta izdelka uspesno dodana."
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Napaka pri preverjanju podatkov."
            )
    })
    @POST
    public Response createVrsta(@RequestBody(
            description = "DTO objekt z informacijami o vrsti izdelka",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Vrsta.class)
            )
    ) Vrsta vrsta) {
        if(vrsta.getVrsta_id() == null || vrsta.getVrsta() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            vrsta = vrstaBean.createVrsta(vrsta);
        }
        return Response.status(Response.Status.CREATED).entity(vrsta).build();
    }

    @Operation(description = "Posodobi informacije o vrsti izdelka.", summary = "Posodobi vrsto izdelka")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Vrsta izdelka uspesno posodobljena"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Vrsta izdelka ne obstaja"
            )
    })
    @PUT
    @Path("{vrstaId}")
    public Response putVrsta(@Parameter(description = "Vrsta ID.", required = true)
                               @PathParam("vrstaId") Integer vrstaId,
                               @RequestBody(
                                       description = "DTO objekt z informacijami o vrsti izdelka.",
                                       required = true,
                                       content = @Content(
                                               schema = @Schema(implementation = Vrsta.class)
                                       )
                               ) Vrsta vrsta) {
        vrsta = vrstaBean.putVrsta(vrstaId, vrsta);
        if(vrsta == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(vrsta).build();
    }

    @Operation(description = "Izbrisi informacije o vrsti izdelka.", summary = "Izbrisi vrsto izdelka")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Vrsta izdelka uspesno izbrisana"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Vrsta izdelka ne obstaja"
            )
    })
    @DELETE
    @Path("{vrstaId}")
    public Response deleteVrsta(@Parameter(description = "Vrsta izdelka ID.", required = true)
                                  @PathParam("vrstaId") Integer vrstaId) {
        boolean deleted = vrstaBean.deleteVrsta(vrstaId);
        if(deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
