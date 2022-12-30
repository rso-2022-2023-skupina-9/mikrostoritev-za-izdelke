package si.fri.rso.skupina09.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.skupina09.converters.TrgovinaConverter;
import si.fri.rso.skupina09.entities.TrgovinaEntity;
import si.fri.rso.skupina09.lib.Trgovina;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class TrgovinaBean {

    private Logger logger = Logger.getLogger(Trgovina.class.getName());

    @Inject
    EntityManager entityManager;

    public List<Trgovina> getTrgovina() {
        TypedQuery<TrgovinaEntity> query = entityManager.createNamedQuery("TrgovinaEntity.getAll", TrgovinaEntity.class);
        List<TrgovinaEntity> result = query.getResultList();
        entityManager.refresh(result);
        return result.stream().map(TrgovinaConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name = "get_trgovine_method")
    public List<Trgovina> getTrgovina(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(entityManager, TrgovinaEntity.class, queryParameters).stream().map(TrgovinaConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name = "get_trgovina_method")
    public Trgovina getTrgovina(Integer id) {
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, id);
        if (trgovinaEntity == null) {
            throw new NotFoundException(String.format("Trgovina z id-jem: %d ne obstaja!", id));
        }
        entityManager.refresh(trgovinaEntity);
        Trgovina trgovina = TrgovinaConverter.toDto(trgovinaEntity);
        return trgovina;
    }

    @Timed(name = "create_trgovina_method")
    public Trgovina createTrgovina(Trgovina trgovina) {
        TrgovinaEntity trgovinaEntity = TrgovinaConverter.toEntity(trgovina);
        try {
            beginTx();
            entityManager.persist(trgovinaEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        if (trgovinaEntity.getTrgovina_id() == null) {
            throw new RuntimeException("Trgovina entity ni bil dodan!");
        }
        return TrgovinaConverter.toDto(trgovinaEntity);
    }

    @Timed(name = "put_trgovina_method")
    public Trgovina putTrgovina(Integer id, Trgovina trgovina) {
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, id);
        if (trgovinaEntity == null) {
            return null;
        }
        TrgovinaEntity updatedTrgovinaEntity = TrgovinaConverter.toEntity(trgovina);
        try {
            beginTx();
            updatedTrgovinaEntity.setTrgovina_id(trgovinaEntity.getTrgovina_id());
            updatedTrgovinaEntity = entityManager.merge(updatedTrgovinaEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return TrgovinaConverter.toDto(updatedTrgovinaEntity);
    }

    @Timed(name = "delete_trgovina_method")
    public boolean deleteTrgovina(Integer id) {
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, id);
        if (trgovinaEntity != null) {
            try {
                beginTx();
                entityManager.remove(trgovinaEntity);
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
