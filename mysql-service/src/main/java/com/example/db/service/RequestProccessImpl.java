package com.example.db.service;

import com.example.db.exception.NotContentInDatabaseException;
import com.example.db.exception.ResourceAlreadyExistsException;
import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.RequestInfoDTO;
import com.example.db.model.entity.ExchangeRate;
import com.example.db.model.entity.RequestInfo;
import com.example.db.repository.ExchangeRateRepository;
import com.example.db.repository.RequestInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestProccessImpl implements RequestProccess{

    private final ExchangeRateRepository exchangeRateRepository;
    private final RequestInfoRepository requestInfoRepository;
    private final ModelMapper modelMapper;


    public RequestProccessImpl(ExchangeRateRepository exchangeRateRepository,
                               RequestInfoRepository requestInfoRepository, ModelMapper modelMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.requestInfoRepository = requestInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void verifyRequestIdDoesNotExist(String requestId) {
        if(requestInfoRepository.existsByRequestId(requestId)){
         throw new ResourceAlreadyExistsException(requestId);
        }
    }

    @Override
    public void saveRequestInfo(RequestInfoDTO requestInfoDTO, Long exchangeRateId) {
        RequestInfo requestInfo = modelMapper.map(requestInfoDTO, RequestInfo.class);
        requestInfo.setExchangeRateId(exchangeRateId);
        requestInfoRepository.save(requestInfo);
    }

    @Override
    public ExchangeRateDto getLatestRates() {
        ExchangeRate rates = exchangeRateRepository.findFirstByOrderByIdDesc().orElseThrow(()->
                new NotContentInDatabaseException("No data present in database."));
        ExchangeRateDto ratesDto = modelMapper.map(rates, ExchangeRateDto.class);
        return ratesDto;
    }
}
