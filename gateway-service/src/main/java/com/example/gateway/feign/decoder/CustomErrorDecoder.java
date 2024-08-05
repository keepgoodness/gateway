package com.example.gateway.feign.decoder;

import com.example.gateway.exception.NotContentInDatabaseException;
import com.example.gateway.exception.ResourceAlreadyExistsException;
import feign.Response;
import feign.codec.ErrorDecoder;
public class CustomErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        String requestId = "";
                if(response.headers().get("request-id") != null){
                 requestId = response.headers().get("request-id").toString();
                }
        System.out.println("***: " + response.status());
        switch (response.status()) {
            case 404:
                return new NotContentInDatabaseException("No data present in database.");
            case 409:
                return new ResourceAlreadyExistsException("Request with id " + requestId + " already exists.");
            default:
                return new Exception("Unknown error occurred");
        }
    }
}
