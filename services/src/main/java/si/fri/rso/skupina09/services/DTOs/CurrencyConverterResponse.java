package si.fri.rso.skupina09.services.DTOs;

public class CurrencyConverterResponse {

    public CurrencyConverterResponse() {}

    public CurrencyConverterResponse(Double _new_amount, String _new_currency, String _old_currency, Double _old_amount) {
        this.new_amount = _new_amount;
        this.new_currency = _new_currency;
        this.old_currency = _old_currency;
        this.old_amount = _old_amount;
    }

    private Double new_amount;
    private String new_currency;
    private String old_currency;
    private Double old_amount;

    public Double getNew_amount() {
        return new_amount;
    }

    public void setNew_amount(Double new_amount) {
        this.new_amount = new_amount;
    }

    public String getNew_currency() {
        return new_currency;
    }

    public void setNew_currency(String new_currency) {
        this.new_currency = new_currency;
    }

    public String getOld_currency() {
        return old_currency;
    }

    public void setOld_currency(String old_currency) {
        this.old_currency = old_currency;
    }

    public Double getOld_amount() {
        return old_amount;
    }

    public void setOld_amount(Double old_amount) {
        this.old_amount = old_amount;
    }
}
