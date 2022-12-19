package si.fri.rso.skupina09.services.DTOs;

public class CurrencyConverterRequest {

    public CurrencyConverterRequest() {}

    public CurrencyConverterRequest(String _have, String _want, Integer _amount) {
        this.have = _have;
        this.want = _want;
        this.amount = _amount;
    }

    private String have;
    private String want;
    private Integer amount;

    public String getHave() {
        return have;
    }

    public void setHave(String have) {
        this.have = have;
    }

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
