package com.example.gateway.model.request.json;

import com.example.gateway.validation.GroupA;
import com.example.gateway.validation.GroupB;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class RequestInfoDto {

    @NotNull(message = "RequestId must not be null. Please provide a valid requestId.", groups = {GroupA.class, GroupB.class})
    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("timestamp")
    private long timestamp;

    @NotNull(message = "Client must not be null. Please provide a valid client value.", groups = {GroupA.class, GroupB.class})
    @JsonProperty("client")
    private String client;

    @JsonProperty("currency")
    private String currency;
    @NotNull(message = "Period must not be null. Please provide a valid period value in hours.",  groups = {GroupB.class})
    @JsonProperty("period")
    private Integer period;

    private String serviceName;

    // Default constructor
    public RequestInfoDto() {}

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
}
