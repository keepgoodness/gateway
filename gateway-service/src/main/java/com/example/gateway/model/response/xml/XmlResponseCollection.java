package com.example.gateway.model.response.xml;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.LinkedHashSet;
import java.util.Set;

@XmlRootElement(name = "rates")
public class XmlResponseCollection {
    private Set<XmlResponse> rate = new LinkedHashSet<>();
}
