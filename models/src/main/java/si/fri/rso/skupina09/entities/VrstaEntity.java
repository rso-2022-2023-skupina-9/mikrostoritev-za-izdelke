package si.fri.rso.skupina09.entities;

import javax.persistence.*;

@Entity
@Table(name = "vrsta")
@NamedQueries(value = {
        @NamedQuery(name = "VrstaEntity.getAll", query = "SELECT vrsta FROM VrstaEntity vrsta")
})
public class VrstaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vrsta_id;
    @Column(name = "vrsta")
    private String vrsta;

    public Integer getVrstaId() {
        return vrsta_id;
    }

    public void setVrstaId(Integer vrstaId) {
        this.vrsta_id = vrstaId;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }
}
