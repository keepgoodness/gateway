package com.example.gateway.client;

import com.example.gateway.configuration.OpenFeignConfig;
import com.example.gateway.model.response.FixerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fixer-service", url = "${fixer_io.api.url}")
public interface FixerClient {
    @GetMapping("${fixer_io.api.path_latest}?access_key=${fixer_io.api.access_key}")
    FixerResponse getLatestCurrencyRates();
}
