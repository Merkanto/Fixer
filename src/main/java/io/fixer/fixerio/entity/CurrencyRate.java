package io.fixer.fixerio.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rates")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quote_currency")
    private String quoteCurrency;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_currency_id", referencedColumnName = "id")
    private BaseCurrency baseCurrency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BaseCurrency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(BaseCurrency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}
