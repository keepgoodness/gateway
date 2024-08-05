package com.repository.fixer.controller;

import com.repository.fixer.model.RatesResponse;
import com.repository.fixer.service.RatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatesController {

    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping("/fixer/api/latest")
    public ResponseEntity<RatesResponse> getRates(
            @RequestParam(name = "access_key", required = false) String accessKey) {
        RatesResponse response = ratesService.prepareRates(accessKey);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
