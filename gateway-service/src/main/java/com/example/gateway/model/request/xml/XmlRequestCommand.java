package com.example.gateway.model.request.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestCommand {
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "get")
    private XmlRequestGet get;
    @XmlElement(name = "history")
    private XmlRequestHistory history;

    public XmlRequestCommand() {
    }

    public XmlRequestCommand(String id, XmlRequestGet get, XmlRequestHistory history) {
        this.id = id;
        this.get = get;
        this.history = history;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public XmlRequestGet getGet() {
        return get;
    }

    public void setGet(XmlRequestGet get) {
        this.get = get;
    }

    public XmlRequestHistory getHistory() {
        return history;
    }

    public void setHistory(XmlRequestHistory history) {
        this.history = history;
    }
}
