package com.iskcon.EthicraftAPI.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO {

    String  successMessage;
    String  errorMessage;
    String  type;
    Integer httpCode;
    Object  data;

    public ResponseDTO() {
    }

    public ResponseDTO(String successMessage, String errorMessage, Integer httpCode) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
    }

    public ResponseDTO(String successMessage, String errorMessage, Integer httpCode, Object data,String type) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
        this.data = data;
        this.type = type;
    }

    public ResponseDTO createSuccessMessage(String successMessage,Object data,Integer httpCode,String type){
        this.data = data;
        this.successMessage = successMessage;
        this.type =type;
        this.httpCode = httpCode;
        return this;
    }
    public ResponseDTO createErrorMessage(String errorMessage,Object data,Integer httpCode,String type){
        this.data = data;
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
        this.type = type;
        return this;
    }

    public static ResponseDTO sendSuccessmessage(String successMessage){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccessMessage(successMessage);
        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.type = "success";
        return responseDTO;
    }
    public static ResponseDTO sendErrorsmessage(String errorMessage){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setErrorMessage(errorMessage);
        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.type = "error";
        return responseDTO;
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

    public String getType() {
        return type;
    }

    public ResponseDTO setType(String type) {
        this.type = type;
        return this;
    }
}
