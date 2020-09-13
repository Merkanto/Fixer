package io.fixer.fixerio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesByDate {

    private String date;

    private Timestamp timestamp;

    private String base;

    private HashMap<String, BigInteger> rates;

    public RatesByDate() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public HashMap<String, BigInteger> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, BigInteger> rates) {
        this.rates = rates;
    }
}
