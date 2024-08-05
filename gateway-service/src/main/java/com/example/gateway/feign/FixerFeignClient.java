package com.example.gateway.feign;

import com.example.gateway.configuration.OpenFeignConfig;
import com.example.gateway.model.response.FixerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "FixerFeignClient",
        url = "${fixer.api.url}",
        configuration = {FixerFeignClientAccessKeyConfiguration.class, OpenFeignConfig.class})
public interface FixerFeignClient {
    @GetMapping("/latest")
    FixerResponse getLatestCurrencyRates();
}
