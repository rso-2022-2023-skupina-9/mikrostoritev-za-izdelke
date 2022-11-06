package si.fri.rso.skupina09.lib;

import java.time.Instant;

public class Trgovina {

    private Integer trgovinaId;

    private String ime;

    private Instant ustanovitev;

    private String sedez;

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
