package com.iskcon.EthicraftAPI.dto;

public class ResponseDTO {

    String successMessage;
    String errorMessage;
    String responseType;
    Integer httpCode;
    Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(String successMessage, String errorMessage, Integer httpCode) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
    }

    public ResponseDTO(String successMessage, String errorMessage, Integer httpCode, Object data,String responseType) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
        this.data = data;
        this.responseType = responseType;
    }

    public ResponseDTO createSuccessMessage(String successMessage,Object data,Integer httpCode,String type){
        this.data = data;
        this.successMessage = successMessage;
        this.responseType =type;
        this.httpCode = httpCode;
        return this;
    }
    public ResponseDTO createErrorMessage(String errorMessage,Object data,Integer httpCode,String type){
        this.data = data;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
        this.responseType = type;
        return this;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
