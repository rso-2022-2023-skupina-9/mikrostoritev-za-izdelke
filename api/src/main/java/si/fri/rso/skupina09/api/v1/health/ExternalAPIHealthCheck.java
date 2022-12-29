package si.fri.rso.skupina09.api.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Liveness
@ApplicationScoped
public class ExternalAPIHealthCheck implements HealthCheck {

    private static final Logger logger = Logger.getLogger(ExternalAPIHealthCheck.class.getName());
    private Client httpClient;
    private String baseUrl;

    @Override
    public HealthCheckResponse call() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "https://currency-converter-by-api-ninjas.p.rapidapi.com/v1/convertcurrency";
        try {
            Response response = httpClient
                    .target(baseUrl)
                    .queryParam("have", "EUR")
                    .queryParam("want", "USD")
                    .queryParam("amount", "1")
                    .request(MediaType.APPLICATION_JSON)
                    .header("X-RapidAPI-Key", "a")
                    .header("X-RapidAPI-Host", "a")
                    .get();
            //SUCCESS
            return HealthCheckResponse.up(ExternalAPIHealthCheck.class.getSimpleName());
        } catch (WebApplicationException | ProcessingException e) {
            //FAILURE
            return HealthCheckResponse.down(ExternalAPIHealthCheck.class.getSimpleName());
        }
    }

}
