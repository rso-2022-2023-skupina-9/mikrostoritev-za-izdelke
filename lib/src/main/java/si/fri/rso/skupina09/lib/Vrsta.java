package si.fri.rso.skupina09.lib;

import java.util.List;

public class Vrsta {

    private Integer vrsta_id;

    private String vrsta;

    private List<Izdelek> izdelki;

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

    public List<Izdelek> getIzdelki() {
        return izdelki;
    }

    public void setIzdelki(List<Izdelek> izdelki) {
        this.izdelki = izdelki;
    }
}
