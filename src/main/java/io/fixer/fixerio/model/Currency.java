package io.fixer.fixerio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    private HashMap<String, String> symbols;

    public Currency() {
    }

    public HashMap<String, String> getSymbols() {
        return symbols;
    }

    public void setSymbols(HashMap<String, String> symbols) {
        this.symbols = symbols;
    }
}
