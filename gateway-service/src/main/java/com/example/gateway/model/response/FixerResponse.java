package com.example.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String base;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Double> rates;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
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
