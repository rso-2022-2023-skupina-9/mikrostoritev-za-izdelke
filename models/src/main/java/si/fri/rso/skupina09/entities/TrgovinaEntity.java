package si.fri.rso.skupina09.entities;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import si.fri.rso.skupina09.lib.Izdelek;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "trgovina")
@NamedQueries(value = {
        @NamedQuery(name = "TrgovinaEntity.getAll", query = "SELECT trgovina FROM TrgovinaEntity trgovina")
})
@Cache(coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class TrgovinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trgovina_id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "ustanovitev")
    private Instant ustanovitev;

    @Column(name = "sedez")
    private String sedez;

    @OneToMany(mappedBy = "TrgovinaEntity")
    private List<IzdelekEntity> izdelki;

    public Integer getTrgovina_id() {
        return trgovina_id;
    }

    public void setTrgovina_id(Integer trgovina_id) {
        this.trgovina_id = trgovina_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Instant getUstanovitev() {
        return ustanovitev;
    }

    public void setUstanovitev(Instant ustanovitev) {
        this.ustanovitev = ustanovitev;
    }

    public String getSedez() {
        return sedez;
    }

    public void setSedez(String sedez) {
        this.sedez = sedez;
    }

    public List<IzdelekEntity> getIzdelki() {
        return izdelki;
    }

    public void setIzdelki(List<IzdelekEntity> izdelki) {
        this.izdelki = izdelki;
    }
}
