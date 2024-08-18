package com.example.gateway.controller;

import com.example.gateway.model.request.xml.XmlRequestCommand;
import com.example.gateway.model.response.xml.XmlResponse;
import com.example.gateway.model.response.xml.XmlResponseWrapper;
import com.example.gateway.service.ExtService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/xml")
public class XmlController {
    private final ExtService service;

    public XmlController(@Qualifier("xmlServiceImpl") ExtService service) {
        this.service = service;
    }

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XmlResponseWrapper> postCommand(@RequestBody XmlRequestCommand request){
        XmlResponseWrapper responseWrapper = new XmlResponseWrapper();
        if(request.getGet() != null){
            responseWrapper.addRate((XmlResponse) service.getLatestRate(request));
        }
        if(request.getHistory() != null){
            responseWrapper.setRate(((List<XmlResponse>) service.getHistoryRates(request)));
        }
        return ResponseEntity.ok(responseWrapper);
    }
}
