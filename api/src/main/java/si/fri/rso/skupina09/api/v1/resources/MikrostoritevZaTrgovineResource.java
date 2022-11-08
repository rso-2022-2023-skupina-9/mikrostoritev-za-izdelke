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
import si.fri.rso.skupina09.lib.Trgovina;
import si.fri.rso.skupina09.services.beans.TrgovinaBean;

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
@Path("/trgovine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE")
public class MikrostoritevZaTrgovineResource {

    private Logger logger = Logger.getLogger(MikrostoritevZaTrgovineResource.class.getName());

    @Inject
    private TrgovinaBean trgovinaBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Pridobi informacije o vseh trgovinah.", summary = "Pridobi vse trgovine")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Seznam vseh trgovin",
                    content = @Content(schema = @Schema(implementation = Trgovina.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Stevilo objektov v seznamu")}
            )
    })
    @GET
    public Response getTrgovina() {
        List<Trgovina> trgovine = trgovinaBean.getTrgovina(uriInfo);
        return Response.status(Response.Status.OK).entity(trgovine).build();
    }

    @Operation(description = "Pridobi informacije o trgovini.", summary = "Pridobi trgovino")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Informacije o trgovini",
                    content = @Content(
                            schema = @Schema(implementation = Trgovina.class)
                    )
            )
    })
    @GET
    @Path("{trgovinaId}")
    public Response getTrgovina(@Parameter(description = "Trgovina ID", required = true)
                               @PathParam("trgovinaId") Integer trgovinaId) {
        Trgovina trgovina = trgovinaBean.getTrgovina(trgovinaId);
        if(trgovina == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(trgovina).build();
    }

    @Operation(description = "Dodaj trgovino.", summary = "Dodaj trgovino")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Trgovina uspesno dodana."
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Napaka pri preverjanju podatkov."
            )
    })
    @POST
    public Response createTrgovina(@RequestBody(
            description = "DTO objekt z informacijami o trgovini",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Trgovina.class)
            )
    ) Trgovina trgovina) {
        if(trgovina.getTrgovina_id() == null || trgovina.getIme() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            trgovina = trgovinaBean.createTrgovina(trgovina);
        }
        return Response.status(Response.Status.CREATED).entity(trgovina).build();
    }

    @Operation(description = "Posodobi informacije o trgovini.", summary = "Posodobi trgovino")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Trgovina uspesno posodobljena"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Trgovina ne obstaja"
            )
    })
    @PUT
    @Path("{trgovinaId}")
    public Response putTrgovina(@Parameter(description = "Trgovina ID.", required = true)
                               @PathParam("trgovinaId") Integer trgovinaId,
                               @RequestBody(
                                       description = "DTO objekt z informacijami o trgovini.",
                                       required = true,
                                       content = @Content(
                                               schema = @Schema(implementation = Trgovina.class)
                                       )
                               ) Trgovina trgovina) {
        trgovina = trgovinaBean.putTrgovina(trgovinaId, trgovina);
        if(trgovina == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(trgovina).build();
    }

    @Operation(description = "Izbrisi informacije o trgovini.", summary = "Izbrisi trgovino")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Trgovina uspesno izbrisana"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Trgovina ne obstaja"
            )
    })
    @DELETE
    @Path("{trgovinaId}")
    public Response deleteTrgovina(@Parameter(description = "Trgovina ID.", required = true)
                                  @PathParam("trgovinaId") Integer trgovinaId) {
        boolean deleted = trgovinaBean.deleteTrgovina(trgovinaId);
        if(deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
