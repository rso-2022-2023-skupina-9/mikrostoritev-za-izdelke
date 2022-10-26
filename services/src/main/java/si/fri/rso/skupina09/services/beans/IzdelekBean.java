package si.fri.rso.skupina09.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina09.converters.IzdelekConverter;
import si.fri.rso.skupina09.entities.IzdelekEntity;
import si.fri.rso.skupina09.entities.TrgovinaEntity;
import si.fri.rso.skupina09.entities.VrstaEntity;
import si.fri.rso.skupina09.lib.Izdelek;

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
public class IzdelekBean {

    private Logger logger = Logger.getLogger(IzdelekBean.class.getName());

    @Inject
    private EntityManager entityManager;

    public List<Izdelek> getIzdelek() {
        TypedQuery<IzdelekEntity> query = entityManager.createNamedQuery("IzdelekEntity.getAll", IzdelekEntity.class);
        List<IzdelekEntity> result = query.getResultList();
        return result.stream().map(IzdelekConverter::toDto).collect(Collectors.toList());
    }

    public List<Izdelek> getIzdelek(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(entityManager, IzdelekEntity.class, queryParameters).stream().map(IzdelekConverter::toDto).collect(Collectors.toList());
    }

    public Izdelek getIzdelek(Integer id) {
        IzdelekEntity izdelekEntity = entityManager.find(IzdelekEntity.class, id);
        if (izdelekEntity == null) {
            throw new NotFoundException(String.format("Izdelek z id-jem: %d ne obstaja!", id));
        }
        Izdelek izdelek = IzdelekConverter.toDto(izdelekEntity);
        return izdelek;
    }

    public Izdelek createIzdelek(Izdelek izdelek) {
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, izdelek.getTrgovinaId());
        if (trgovinaEntity == null) {
            throw new NotFoundException(String.format("Trgovina z id-jem: %d ne obstaja!", izdelek.getTrgovinaId()));
        }
        VrstaEntity vrstaEntity = entityManager.find(VrstaEntity.class, izdelek.getVrstaId());
        if (vrstaEntity == null) {
            throw new NotFoundException(String.format("Vrsta z id-jem: %d ne obstaja!", izdelek.getVrstaId()));
        }
        IzdelekEntity izdelekEntity = IzdelekConverter.toEntity(izdelek, trgovinaEntity, vrstaEntity);
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

    public Izdelek putIzdelek(Integer id, Izdelek izdelek) {
        IzdelekEntity izdelekEntity = entityManager.find(IzdelekEntity.class, id);
        if (izdelekEntity == null) {
            return null;
        }
        TrgovinaEntity trgovinaEntity = entityManager.find(TrgovinaEntity.class, izdelek.getTrgovinaId());
        if (trgovinaEntity == null) {
            throw new NotFoundException(String.format("Trgovina z id-jem: %d ne obstaja!", izdelek.getTrgovinaId()));
        }
        VrstaEntity vrstaEntity = entityManager.find(VrstaEntity.class, izdelek.getVrstaId());
        if (vrstaEntity == null) {
            throw new NotFoundException(String.format("Vrsta z id-jem: %d ne obstaja!", izdelek.getVrstaId()));
        }
        IzdelekEntity updatedIzdelekEntity = IzdelekConverter.toEntity(izdelek, trgovinaEntity, vrstaEntity);
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

    public boolean deleteIzdelek(Integer id) {
        IzdelekEntity izdelekEntity = entityManager.find(IzdelekEntity.class, id);
        if(izdelekEntity != null) {
            try {
                beginTx();
                entityManager.remove(izdelekEntity);
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
