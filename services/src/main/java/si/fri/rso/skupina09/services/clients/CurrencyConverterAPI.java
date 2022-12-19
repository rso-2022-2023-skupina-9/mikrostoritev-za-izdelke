package si.fri.rso.skupina09.services.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.rso.skupina09.services.DTOs.CurrencyConverterRequest;
import si.fri.rso.skupina09.services.DTOs.CurrencyConverterResponse;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;

@Path("convert")
@RegisterRestClient(configKey = "currency-converter-api")
@Dependent
public interface CurrencyConverterAPI {

    @GET
    CompletionStage<CurrencyConverterResponse> convertCurrencyAsync(CurrencyConverterRequest currencyConverterRequest);
}
