package si.fri.rso.skupina09.lib;

import java.time.Instant;

public class Trgovina {

    private Integer trgovina_id;

    private String ime;

    private Instant ustanovitev;

    private String sedez;

    public Integer getTrgovina_id() {
        return trgovina_id;
    }

    public void setTrgovina_id(Integer trgovinaId) {
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
