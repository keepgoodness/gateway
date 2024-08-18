package com.example.gateway.service;

import java.util.List;

public interface ExtService<T, U> {

    T getLatestRate(U request);

    List<T> getHistoryRates(U request);
}
