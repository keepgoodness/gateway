package com.example.gateway.model.response.xml;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "rates")
public class XmlResponseWrapper {
    private List<XmlResponse> rate = new LinkedList<>();
    public List<XmlResponse> getRate() {
        return rate;
    }
    public void setRate(List<XmlResponse> rate) {
        this.rate = rate;
    }
    public void addRate(XmlResponse rate){
        this.rate.add(rate);
    }
}
