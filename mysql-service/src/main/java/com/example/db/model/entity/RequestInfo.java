package com.example.db.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "request_info")
public class RequestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "request_id", unique = true)
    private String requestId;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "client")
    private String client;

    @Column(name = "currency")
    private String currency;

//    private Long exchangeRateId;

    @ManyToMany
    @JoinTable(name = "request_info_exchange_rates",
            joinColumns = @JoinColumn(name = "request_info_id"),
    inverseJoinColumns = @JoinColumn(name = "exchange_rate_id"))
    private List<ExchangeRate> exchangeRates = new ArrayList<>();

    public RequestInfo() {}

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

//    public Long getExchangeRateId() {
//        return exchangeRateId;
//    }

//    public void setExchangeRateId(Long exchangeRateId) {
//        this.exchangeRateId = exchangeRateId;
//    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public void addExchangeRate(ExchangeRate exchangeRate){
        this.exchangeRates.add(exchangeRate);
    }

    public void removeExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRates.remove(exchangeRate);
    }
}
