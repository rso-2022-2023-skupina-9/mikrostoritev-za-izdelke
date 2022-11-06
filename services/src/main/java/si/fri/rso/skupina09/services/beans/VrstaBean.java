package si.fri.rso.skupina09.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina09.converters.VrstaConverter;
import si.fri.rso.skupina09.entities.VrstaEntity;
import si.fri.rso.skupina09.lib.Vrsta;

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
public class VrstaBean {

    private Logger logger = Logger.getLogger(Vrsta.class.getName());

    @Inject
    EntityManager entityManager;

    public List<Vrsta> getVrsta() {
        TypedQuery<VrstaEntity> query = entityManager.createNamedQuery("VrstaEntity.getAll", VrstaEntity.class);
        List<VrstaEntity> result = query.getResultList();
        return result.stream().map(VrstaConverter::toDto).collect(Collectors.toList());
    }

    public List<Vrsta> getVrsta(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(entityManager, VrstaEntity.class, queryParameters).stream().map(VrstaConverter::toDto).collect(Collectors.toList());
    }

    public Vrsta getVrsta(Integer id) {
        VrstaEntity vrstaEntity = entityManager.find(VrstaEntity.class, id);
        if (vrstaEntity == null) {
            throw new NotFoundException(String.format("Vrsta z id-jem: %d ne obstaja!", id));
        }
        Vrsta vrsta = VrstaConverter.toDto(vrstaEntity);
        return vrsta;
    }

    public Vrsta createVrsta(Vrsta vrsta) {
        VrstaEntity vrstaEntity = VrstaConverter.toEntity(vrsta);
        try {
            beginTx();
            entityManager.persist(vrstaEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        if (vrstaEntity.getVrstaId() == null) {
            throw new RuntimeException("Vrsta entity ni bil dodan!");
        }
        return VrstaConverter.toDto(vrstaEntity);
    }

    public Vrsta putVrsta(Integer id, Vrsta vrsta) {
        VrstaEntity vrstaEntity = entityManager.find(VrstaEntity.class, id);
        if (vrstaEntity == null) {
            return null;
        }
        VrstaEntity updatedVrstaEntity = VrstaConverter.toEntity(vrsta);
        try {
            beginTx();
            updatedVrstaEntity.setVrstaId(vrstaEntity.getVrstaId());
            updatedVrstaEntity = entityManager.merge(updatedVrstaEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return VrstaConverter.toDto(updatedVrstaEntity);
    }

    public boolean deleteVrsta(Integer id) {
        VrstaEntity vrstaEntity = entityManager.find(VrstaEntity.class, id);
        if (vrstaEntity != null) {
            try {
                beginTx();
                entityManager.remove(vrstaEntity);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
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
