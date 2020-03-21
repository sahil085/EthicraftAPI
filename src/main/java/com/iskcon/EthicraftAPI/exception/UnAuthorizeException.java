package com.iskcon.EthicraftAPI.exception;

public class UnAuthorizeException extends RuntimeException {

    public UnAuthorizeException(String exceptionMessage){
        super(exceptionMessage.isEmpty() ? "Unauthorized access" : exceptionMessage);
    }
}
