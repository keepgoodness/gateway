package com.example.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorDetails {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String info;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
