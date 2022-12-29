package si.fri.rso.skupina09.services.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;

@Path("kosarice/stevilo")
@RegisterRestClient(configKey = "kosarice-api")
@Dependent
public interface SteviloKosaricAPI {

    @GET
    CompletionStage<Integer> pridobiKosariceAsynch();
}
