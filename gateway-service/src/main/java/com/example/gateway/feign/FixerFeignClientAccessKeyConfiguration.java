package com.example.gateway.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixerFeignClientAccessKeyConfiguration {

    @Value("${fixer.api.key}")
    private String accessKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // Add access_key as a query parameter to every request
                if (!template.url().contains("access_key")) {
                    template.query("access_key", accessKey);
                }
            }
        };
    }
}
