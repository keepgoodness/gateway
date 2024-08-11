package com.example.gateway.client.decoder;

import com.example.gateway.exception.DataNotFoundException;
import com.example.gateway.exception.ResourceAlreadyExistsException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String requestId = "";
        String currency = "";
        if (response.headers().get("request-id") != null) {
            requestId = response.headers().get("request-id").toString();
        }
        if (response.headers().get("currency") != null) {
            currency = response.headers().get("currency").toString();
        }
        switch (response.status()) {
            case 404:
                return new DataNotFoundException(String.format("Data for currency %s is not found.", currency));
            case 409:
                return new ResourceAlreadyExistsException(String.format("Request with id: %s already exists", requestId));
            default:
                return new Exception("Unknown error occurred");
        }
    }
}
