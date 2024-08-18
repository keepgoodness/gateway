package com.example.db.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GatewayRequestDTO {

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("client")
    private String client;

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("period")
    private Integer period;

    private String serviceName;

    // Default constructor
    public GatewayRequestDTO() {}

    // Parameterized constructor
    public GatewayRequestDTO(String requestId, long timestamp, String client, String currency) {
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.client = client;
        this.currency = currency;
    }

    // Getters and Setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "GatewayRequestDTO{" +
                "requestId='" + requestId + '\'' +
                ", timestamp=" + timestamp +
                ", client='" + client + '\'' +
                ", currency='" + currency + '\'' +
                ", period=" + period +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
