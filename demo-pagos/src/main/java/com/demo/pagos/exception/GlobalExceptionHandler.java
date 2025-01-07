package com.demo.pagos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.java.Log;


@ControllerAdvice
@Log
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpException.class)
  public ResponseEntity<String> handleException(HttpException ex) {
    log.warning(ex.getMessage());
    return new ResponseEntity<>(ex.getMessage(), ex.getCode());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleException(Exception ex) {
    log.warning(ex.getMessage());
    return new ResponseEntity<>("error interno", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
