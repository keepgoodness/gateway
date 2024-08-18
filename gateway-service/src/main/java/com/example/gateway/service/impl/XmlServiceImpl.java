package com.example.gateway.service.impl;

import com.example.gateway.client.MysqlClient;
import com.example.gateway.model.request.json.RequestInfoDto;
import com.example.gateway.model.request.xml.XmlRequestCommand;
import com.example.gateway.model.response.RateResponseDto;
import com.example.gateway.model.response.xml.XmlResponse;
import com.example.gateway.service.ExtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "xmlServiceImpl")
public class XmlServiceImpl implements ExtService<XmlResponse, XmlRequestCommand> {
    private final static String SERVICE_NAME= "EXT_SERVICE_2";

    private final MysqlClient mysqlClient;
    private final ModelMapper mapper;


    public XmlServiceImpl(MysqlClient mysqlClient, ModelMapper mapper) {
        this.mysqlClient = mysqlClient;
        this.mapper = mapper;
    }

    @Override
    public XmlResponse getLatestRate(XmlRequestCommand request) {
        RequestInfoDto requestInfoDto = new RequestInfoDto();
        requestInfoDto.setServiceName(SERVICE_NAME);
        requestInfoDto.setRequestId(request.getId());
        requestInfoDto.setClient(request.getGet().getConsumer());
        requestInfoDto.setCurrency(request.getGet().getCurrency());

        RateResponseDto rateResponse = mysqlClient.sendRequestAndReceiveCurrentRates(requestInfoDto);

        XmlResponse xmlResponse = mapper.map(rateResponse, XmlResponse.class);
        xmlResponse.setTimestamp(rateResponse.getTimestamp().toString());
        return xmlResponse;
    }

    @Override
    public List<XmlResponse> getHistoryRates(XmlRequestCommand request) {
        RequestInfoDto requestInfoDto = new RequestInfoDto();
        requestInfoDto.setServiceName(SERVICE_NAME);
        requestInfoDto.setRequestId(request.getId());
        requestInfoDto.setClient(request.getHistory().getConsumer());
        requestInfoDto.setCurrency(request.getHistory().getCurrency());
        requestInfoDto.setPeriod(request.getHistory().getPeriod());

        return mysqlClient.sendRequestAndReceiveRatesHistory(requestInfoDto).stream()
                .map(rate -> {
                    XmlResponse xmlResponse = mapper.map(rate, XmlResponse.class);
                    xmlResponse.setTimestamp(rate.getTimestamp().toString()); // Handling timestamp separately
                    return xmlResponse;
                })
                .collect(Collectors.toList());
    }
}
