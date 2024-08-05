package com.example.gateway.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class ExchangeRateDto {

    @JsonIgnore
    private Long id;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    public ExchangeRateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
