package com.example.gateway.service.impl;

import com.example.gateway.client.MysqlClient;
import com.example.gateway.model.request.json.RequestInfoDto;
import com.example.gateway.model.response.RateResponseDto;
import com.example.gateway.service.ExtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "jsonServiceImpl")
public class JsonServiceImpl implements ExtService<RateResponseDto, RequestInfoDto> {
    private final MysqlClient mysqlClient;
    private final static String SERVICE_NAME= "EXT_SERVICE_1";

    public JsonServiceImpl(MysqlClient mysqlClient) {
        this.mysqlClient = mysqlClient;
    }

    @Override
    public RateResponseDto getLatestRate(RequestInfoDto request) {
        request.setServiceName(SERVICE_NAME);
        return mysqlClient.sendRequestAndReceiveCurrentRates(request);
    }

    @Override
    public List<RateResponseDto> getHistoryRates(RequestInfoDto request) {
        request.setServiceName(SERVICE_NAME);
        return mysqlClient.sendRequestAndReceiveRatesHistory(request);
    }
}
