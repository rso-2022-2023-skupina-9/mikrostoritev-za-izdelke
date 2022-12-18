package si.fri.rso.skupina09.entities;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "izdelek")
@NamedQueries(value = {
        @NamedQuery(name = "IzdelekEntity.getAll", query = "SELECT izdelek FROM IzdelekEntity izdelek")
})
@Cache(coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class IzdelekEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer izdelek_id;

    @ManyToOne
    @JoinColumn(name = "vrsta_id")
    private VrstaEntity vrstaEntity;

    @ManyToOne
    @JoinColumn(name = "trgovina_id")
    private TrgovinaEntity trgovinaEntity;

    @Column(name = "ime")
    private String ime;

    @Column(name = "cena")
    private Integer cena;

    @Column(name = "zadnja_sprememba")
    private Instant zadnjaSprememba;

    public Integer getIzdelekId() {
        return izdelek_id;
    }

    public void setIzdelekId(Integer izdelekId) {
        this.izdelek_id = izdelekId;
    }

    public VrstaEntity getVrstaEntity() { return vrstaEntity; }

    public void setVrstaEntity(VrstaEntity vrstaEntity) { this.vrstaEntity = vrstaEntity; }

    public TrgovinaEntity getTrgovinaEntity() { return trgovinaEntity; }

    public void setTrgovinaEntity(TrgovinaEntity trgovinaEntity) {
        this.trgovinaEntity = trgovinaEntity;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Instant getZadnjaSprememba() {
        return zadnjaSprememba;
    }

    public void setZadnjaSprememba(Instant zadnjaSprememba) {
        this.zadnjaSprememba = zadnjaSprememba;
    }
}
