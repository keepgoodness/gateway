package com.example.gateway.model.request.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestHistory {

    @XmlAttribute(name = "consumer")
    private String consumer;
    @XmlAttribute(name = "currency")
    private String currency;
    @XmlAttribute(name = "period")
    private Integer period;

    public XmlRequestHistory() {
    }

    public XmlRequestHistory(String consumer, String currency, Integer period) {
        this.consumer = consumer;
        this.currency = currency;
        this.period = period;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
