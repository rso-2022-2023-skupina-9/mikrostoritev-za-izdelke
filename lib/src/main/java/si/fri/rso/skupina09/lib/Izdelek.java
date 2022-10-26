package si.fri.rso.skupina09.lib;

import java.time.Instant;

public class Izdelek {

    private Integer izdelekId;
    private Integer vrstaId;
    private Integer trgovinaId;
    private String ime;
    private Integer cena;
    private Instant zadnjaSprememba;

    public Integer getIzdelekId() {
        return izdelekId;
    }

    public void setIzdelekId(Integer izdelekId) {
        this.izdelekId = izdelekId;
    }

    public Integer getVrstaId() {
        return vrstaId;
    }

    public void setVrstaId(Integer vrstaId) {
        this.vrstaId = vrstaId;
    }

    public Integer getTrgovinaId() {
        return trgovinaId;
    }

    public void setTrgovinaId(Integer trgovinaId) {
        this.trgovinaId = trgovinaId;
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
