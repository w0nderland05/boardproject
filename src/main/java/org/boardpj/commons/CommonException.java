package org.boardpj.commons;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class CommonException extends RuntimeException{

    protected static ResourceBundle bundleValidation; //메세지코드
    protected  static ResourceBundle bundleError;//메세지코드

    protected HttpStatus httpStatus; //응답코드

    static{
        bundleValidation = ResourceBundle.getBundle("messages.validations");
        bundleError = ResourceBundle.getBundle("messages.errors");

    }
    public CommonException(String message){
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CommonException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus= httpStatus;
    }

    public HttpStatus getStatus(){

        return httpStatus;
    }
}
