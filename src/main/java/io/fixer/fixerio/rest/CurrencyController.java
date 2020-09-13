package io.fixer.fixerio.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.fixer.fixerio.entity.CurrencyRate;
import io.fixer.fixerio.model.Currency;
import io.fixer.fixerio.model.RatesByDate;
import io.fixer.fixerio.model.RatesByPeriod;
import io.fixer.fixerio.persist.RateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CurrencyController {

    private RestTemplate restTemplate;
    private RateRepository rateRepository;

    @Value("${fixer.api.key}")
    private String fixerApiKey;

    public CurrencyController(RestTemplateBuilder builder, RateRepository rateRepository) {
        restTemplate = builder.build();
        this.rateRepository = rateRepository;
    }

    @GetMapping("/currencies")
    public String getCurrencies() throws JsonProcessingException {
        Currency currencies = restTemplate.getForObject("http://data.fixer.io/api/symbols?access_key=" + fixerApiKey, Currency.class);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(currencies.getSymbols());
        return result;
    }

    // not supported by Free plan
    // it is supported by Professional plan and above
    @GetMapping("/rates/historic/{base}")
    public String getAllHistoricRates(@PathVariable String base) throws JsonProcessingException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = formatter.parse(formatter.format(new Date()));
        RatesByPeriod rates = restTemplate.getForObject(
                "http://data.fixer.io/api/timeseries?access_key=" + fixerApiKey + "&start_date=1999-01-01&end_date="
                        + currentDate.toString()
                        + "&base="
                        + base, RatesByPeriod.class);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(rates.getRates());
        if (rates.getRates() == null) {
            return "{ \"error\" : " +
                    "\"The current subscription plan does not support this API endpoint.\" }";
        }
        return result;
    }

    // not supported by Free plan
    // it is supported by Basic plan and above
    @GetMapping("/rates/historic/{base}/{date}")
    public String getHistoricRateByDate(@PathVariable String base, @PathVariable String date) throws JsonProcessingException {
        RatesByDate rates = restTemplate.getForObject(
                "http://data.fixer.io/api/" + date + "?access_key=" + fixerApiKey + "&base=" + base, RatesByDate.class);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(rates.getRates());
        if (rates.getRates() == null) {
            return "{ \"error\" : " +
                    "\"The current subscription plan does not support this API endpoint.\" }";
        }
        return result;
    }

    // not supported by Free plan
    // it is supported by Basic plan and above
    @GetMapping("/rates/latest/{base}")
    public String getLatestRate(@PathVariable String base) throws JsonProcessingException{
        RatesByDate rates = restTemplate.getForObject(
                "http://data.fixer.io/api/latest?access_key=" + fixerApiKey + "&base=" + base, RatesByDate.class);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(rates.getRates());
        if (rates.getRates() == null) {
            return "{ \"error\" : " +
                    "\"The current subscription plan does not support this API endpoint.\" }";
        }
        return result;
    }

    @GetMapping("/report/{currency}/{date}")
    public String getReportRates(@PathVariable String currency, @PathVariable String date) throws JsonProcessingException {

        Iterable<CurrencyRate> rates = rateRepository.findAll();
        List<CurrencyRate> finalCurrencyRates = new ArrayList<CurrencyRate>();
        for (CurrencyRate rate: rates) {
            String dateFromDatabase = rate.getBaseCurrency().getDate().toString();
            String currencyFromDatabase = rate.getBaseCurrency().getCurrency();

            if (dateFromDatabase.equals(date) &&
                    currencyFromDatabase.equals(currency)){
                finalCurrencyRates.add(rate);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(finalCurrencyRates);

        return result;
    }
}
