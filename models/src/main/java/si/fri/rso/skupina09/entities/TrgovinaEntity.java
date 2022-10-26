package si.fri.rso.skupina09.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "trgovina")
@NamedQueries(value = {
        @NamedQuery(name = "TrgovinaEntity.getAll", query = "SELECT trgovina FROM TrgovinaEntity trgovina")
})
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

    public Integer getTrgovinaId() {
        return trgovina_id;
    }

    public void setTrgovinaId(Integer trgovinaId) {
        this.trgovina_id = trgovinaId;
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
}
