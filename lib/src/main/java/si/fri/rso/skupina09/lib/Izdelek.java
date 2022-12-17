package si.fri.rso.skupina09.lib;

import java.time.Instant;

public class Izdelek {

    private Integer izdelek_id;
    private Vrsta vrsta;
    private Trgovina trgovina;
    private String ime;
    private Integer cena;
    private Instant zadnja_sprememba;

    public Integer getIzdelek_id() {
        return izdelek_id;
    }

    public void setIzdelek_id(Integer izdelek_id) {
        this.izdelek_id = izdelek_id;
    }

    public Vrsta getVrsta() {
        return vrsta;
    }

    public void setVrsta(Vrsta vrsta) {
        this.vrsta = vrsta;
    }

    public Trgovina getTrgovina() {
        return trgovina;
    }

    public void setTrgovina(Trgovina trgovina) {
        this.trgovina = trgovina;
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

    public Instant getZadnja_sprememba() {
        return zadnja_sprememba;
    }

    public void setZadnja_sprememba(Instant zadnja_sprememba) {
        this.zadnja_sprememba = zadnja_sprememba;
    }
}
