package com.example.gateway.model.request.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestGet {
    @XmlAttribute(name = "consumer")
    private String consumer;
    @XmlElement(name = "currency")
    private String currency;

    public XmlRequestGet() {
    }

    public XmlRequestGet(String consumer, String currency) {
        this.consumer = consumer;
        this.currency = currency;
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
}
