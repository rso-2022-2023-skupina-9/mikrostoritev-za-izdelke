package si.fri.rso.skupina09.lib;

import java.time.Instant;

public class Izdelek {

    private Integer izdelek_id;
    private Integer vrsta_id;
    private Integer trgovina_id;
    private String ime;
    private Integer cena;
    private Instant zadnja_sprememba;

    public Integer getIzdelek_id() { return izdelek_id; }

    public void setIzdelek_id(Integer izdelek_id) {
        this.izdelek_id = izdelek_id;
    }

    public Integer getVrsta_id() {
        return vrsta_id;
    }

    public void setVrsta_id(Integer vrsta_id) {
        this.vrsta_id = vrsta_id;
    }

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
