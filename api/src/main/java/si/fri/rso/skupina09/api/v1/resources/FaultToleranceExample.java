package si.fri.rso.skupina09.api.v1.resources;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.skupina09.services.config.ConfigProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/fault-tolerance")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FaultToleranceExample {

    private Logger logger = Logger.getLogger(FaultToleranceExample.class.getName());

    @Inject
    private ConfigProperties configProperties;

    private Client httpClient;

    @Operation(description = "Pridobi stevilo kosarica.", summary = "Pridobi stevilo kosaric")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Stevilo kosarica"
            )
    })
    @GET
    public Response getSteviloKosaric() {
        Integer steviloKosaric = pridobiSteviloKosaric();
        return Response.status(Response.Status.OK).entity(String.format("{\"stevilo_kosaric\": \"%d\"}", steviloKosaric)).build();
    }

    @Timeout
    @CircuitBreaker
    @Fallback(fallbackMethod = "pridobiSteviloKosaricFallback")
    public Integer pridobiSteviloKosaric() {
        logger.info("Circuit Breaker Demo");
        httpClient = ClientBuilder.newClient();
        try {
            return httpClient.target(configProperties.getKosariceAPIURL() + "/kosarice/stevilo").request(MediaType.APPLICATION_JSON).get(Integer.class);
        } catch (Exception e) {
            logger.severe("Method: pridobiSteviloKosaric failed! (" + e.getMessage() + ")");
            throw new InternalServerErrorException(e);
        }
    }

    public Integer pridobiSteviloKosaricFallback() {
        logger.info("FALLBACK METODA ZA PRIDOBIVANJE KOSARIC!");
        return Integer.valueOf(-1);
    }
}
