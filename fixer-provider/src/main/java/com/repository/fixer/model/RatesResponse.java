package com.repository.fixer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.repository.fixer.util.CustomDoubleSerializer;

import java.util.Map;

public class RatesResponse {
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String base;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(contentUsing = CustomDoubleSerializer.class)
    private Map<String, Double> rates;

    // Getters and setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }
}
