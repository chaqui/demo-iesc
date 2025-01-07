package com.demo.pagos.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class HttpException extends Exception {

    private static final long serialVersionUID = 1L;
    private HttpStatus code;

    public HttpException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

}
