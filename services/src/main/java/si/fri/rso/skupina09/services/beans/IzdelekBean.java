package si.fri.rso.skupina09.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.skupina09.converters.IzdelekConverter;
import si.fri.rso.skupina09.entities.IzdelekEntity;
import si.fri.rso.skupina09.entities.TrgovinaEntity;
import si.fri.rso.skupina09.entities.VrstaEntity;
import si.fri.rso.skupina09.lib.Izdelek;
import si.fri.rso.skupina09.services.DTOs.CurrencyConverterRequest;
import si.fri.rso.skupina09.services.DTOs.CurrencyConverterResponse;
import si.fri.rso.skupina09.services.config.ConfigProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class IzdelekBean {

    private Logger logger = Logger.getLogger(IzdelekBean.class.getName());

    @Inject
    private EntityManager entityManager;

    @Inject
    private ConfigProperties configProperties;

    private Client httpClient;
    private String baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "https://currency-converter-by-api-ninjas.p.rapidapi.com/v1/convertcurrency";
    }

    public List<Izdelek> getIzdelek() {
        TypedQuery<IzdelekEntity> query = entityManager.createNamedQuery("IzdelekEntity.getAll", IzdelekEntity.class);
        List<IzdelekEntity> result = query.getResultList();
        return result.stream().map(IzdelekConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name = "get_izdelki_method")
    public List<Izdelek> getIzdelek(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(entityManager, IzdelekEntity.class, queryParameters).stream().map(IzdelekConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name = "get_izdelek_method")
    public Izdelek getIzdelek(Integer id) {
        IzdelekEntity izdelekEntity = entityManager.find(IzdelekEntity.class, id);
        if (izdelekEntity == null) {
            throw new NotFoundException(String.format("Izdelek z id-jem: %d ne obstaja!", id));
        }
        entityManager.refresh(izdelekEntity);
        Izdelek izdelek = IzdelekConverter.toDto(izdelekEntity);
        return izdelek;
    }

    @Timed(name = "create_izdelek_method")
    public Izdelek createIzdelek(Izdelek izdelek) {
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, izdelek.getTrgovina().getTrgovina_id());
        if (trgovinaEntity == null) {
            throw new NotFoundException(String.format("Trgovina z id-jem: %d ne obstaja!", izdelek.getTrgovina().getTrgovina_id()));
        }
        IzdelekEntity izdelekEntity = IzdelekConverter.toEntity(izdelek);
        try {
            beginTx();
            entityManager.persist(izdelekEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        if (izdelekEntity.getIzdelekId() == null) {
            throw new RuntimeException("Izdelek entity ni bil dodan!");
        }
        return IzdelekConverter.toDto(izdelekEntity);
    }

    @Timed(name = "put_izdelek_method")
    public Izdelek putIzdelek(Integer id, Izdelek izdelek) {
        IzdelekEntity izdelekEntity = entityManager.find(IzdelekEntity.class, id);
        if (izdelekEntity == null) {
            return null;
        }
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, izdelek.getTrgovina().getTrgovina_id());
        if (trgovinaEntity == null) {
            throw new NotFoundException(String.format("Trgovina z id-jem: %d ne obstaja!", izdelek.getTrgovina().getTrgovina_id()));
        }
        IzdelekEntity updatedIzdelekEntity = IzdelekConverter.toEntity(izdelek);
        try {
            beginTx();
            updatedIzdelekEntity.setIzdelekId(izdelekEntity.getIzdelekId());
            updatedIzdelekEntity = entityManager.merge(updatedIzdelekEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return IzdelekConverter.toDto(updatedIzdelekEntity);
    }

    @Timed(name = "delete_izdelek_method")
    public boolean deleteIzdelek(Integer id) {
        IzdelekEntity izdelekEntity = entityManager.find(IzdelekEntity.class, id);
        if(izdelekEntity != null) {
            try {
                beginTx();
                entityManager.remove(izdelekEntity);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public CurrencyConverterResponse convertCurrency(CurrencyConverterRequest currencyConverterRequest) {
        logger.info("Calling API for currency conversion!");
        try {
            CurrencyConverterResponse response = httpClient
                    .target(baseUrl)
                    .queryParam("have", currencyConverterRequest.getHave())
                    .queryParam("want", currencyConverterRequest.getWant())
                    .queryParam("amount", currencyConverterRequest.getAmount())
                    .request(MediaType.APPLICATION_JSON)
                    .header("X-RapidAPI-Key", configProperties.getxRapidAPIKey())
                    .header("X-RapidAPI-Host", configProperties.getxRapidAPIHost())
                    .get(CurrencyConverterResponse.class);
            return response;
        } catch (WebApplicationException | ProcessingException e) {
            logger.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    private void beginTx() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

}
