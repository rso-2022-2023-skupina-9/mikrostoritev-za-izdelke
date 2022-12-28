package si.fri.rso.skupina09.lib;

import java.util.List;

public class Kosarica {

    private Integer kosarica_id;
    private List<Izdelek> izdelki;
    private String ime;

    public Integer getKosarica_id() {
        return kosarica_id;
    }

    public void setKosarica_id(Integer kosarica_id) {
        this.kosarica_id = kosarica_id;
    }

    public List<Izdelek> getIzdelki() {
        return izdelki;
    }

    public void setIzdelki(List<Izdelek> izdelki) {
        this.izdelki = izdelki;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
