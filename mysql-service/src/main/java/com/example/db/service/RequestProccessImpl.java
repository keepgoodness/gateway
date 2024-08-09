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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestProccessImpl implements RequestProccess {

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
        if (requestInfoRepository.existsByRequestId(requestId)) {
            throw new ResourceAlreadyExistsException(requestId);
        }
    }

    @Override
    public ExchangeRateDto getLatestRates(RequestInfoDTO requestInfoDTO) {
        RequestInfo requestInfo = modelMapper.map(requestInfoDTO, RequestInfo.class);
        ExchangeRateDto exchangeRateDto = null;

        Optional<ExchangeRate> optional = exchangeRateRepository.findFirstByOrderByIdDesc();
        if (optional.isPresent()) {
            ExchangeRate exchangeRate = optional.get();
            requestInfo.addExchangeRate(exchangeRate);
            exchangeRateDto = convertToDto(exchangeRate);
        }

        requestInfoRepository.save(requestInfo);
        return exchangeRateDto;
    }

    @Override
    public ExchangeRateDto convertToDto(ExchangeRate exchangeRate) {
        ExchangeRateDto ratesDto = modelMapper.map(exchangeRate, ExchangeRateDto.class);
        return ratesDto;
    }
}
