package com.example.db.controller;

import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.RequestInfoDTO;
import com.example.db.service.RequestProccess;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class ExchangeRateJsonController {
    private final RequestProccess requestProccess;
    public ExchangeRateJsonController(RequestProccess requestProccess) {
        this.requestProccess = requestProccess;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateDto> getLatestRate(@RequestBody RequestInfoDTO requestInfoDTO){
        requestProccess.verifyRequestIdDoesNotExist(requestInfoDTO.getRequestId());
        return ResponseEntity.ok(requestProccess.getLatestRates(requestInfoDTO));
    }

//    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ExchangeRateDto>> getRatesFromLastHours(@RequestBody RequestInfoDTO requestInfo){
//        List<ExchangeRateDto> ratesHistory = requestProccess.getHistoryRates(requestInfo);
//        return ResponseEntity.ok(ratesHistory);
//    }
}
