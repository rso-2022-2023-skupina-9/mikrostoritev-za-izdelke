package si.fri.rso.skupina09.entities;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vrsta")
@NamedQueries(value = {
        @NamedQuery(name = "VrstaEntity.getAll", query = "SELECT vrsta FROM VrstaEntity vrsta")
})
@Cache(coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class VrstaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vrsta_id;
    @Column(name = "vrsta")
    private String vrsta;

    @OneToMany(mappedBy = "VrstaEntity")
    private List<IzdelekEntity> izdelki;

    public Integer getVrsta_id() {
        return vrsta_id;
    }

    public void setVrsta_id(Integer vrsta_id) {
        this.vrsta_id = vrsta_id;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public List<IzdelekEntity> getIzdelki() {
        return izdelki;
    }

    public void setIzdelki(List<IzdelekEntity> izdelki) {
        this.izdelki = izdelki;
    }
}
