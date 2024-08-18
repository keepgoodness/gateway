package com.example.gateway.model.response.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResponse {
    @XmlElement(name = "baseCurrency")
    private String baseCurrency;

    @XmlElement(name = "currency")
    private String currency;

    @XmlElement(name = "rate")
    private double rate;

    @XmlElement(name = "timestamp")
    private String timestamp;

    public XmlResponse() {
    }
    public XmlResponse(String baseCurrency, String currency, double rate, String timestamp) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
