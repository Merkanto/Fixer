package io.fixer.fixerio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesByPeriod {

    private String base;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    private HashMap<String, HashMap<String, BigDecimal>> rates;

    public RatesByPeriod() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public HashMap<String, HashMap<String, BigDecimal>> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, HashMap<String, BigDecimal>> rates) {
        this.rates = rates;
    }
}
