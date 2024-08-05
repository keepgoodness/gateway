package com.example.gateway.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class RequestInfoDto {

    @NotNull(message = "RequestId is required.")
    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("client")
    private String client;

    @JsonProperty("currency")
    private String currency;
    @NotNull(message = "Period is required(in hours).")
    @JsonProperty("period")
    private Integer period;

    // Default constructor
    public RequestInfoDto() {}

    // Parameterized constructor
    public RequestInfoDto(String requestId, long timestamp, String client, String currency) {
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

    @Override
    public String toString() {
        return "RequestInfoDTO{" +
                "requestId='" + requestId + '\'' +
                ", timestamp=" + timestamp +
                ", client='" + client + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
