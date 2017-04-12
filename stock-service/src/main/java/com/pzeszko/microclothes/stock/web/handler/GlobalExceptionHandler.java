package com.pzeszko.microclothes.stock.web.handler;

import com.pzeszko.microclothes.stock.exception.MicroclothesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Admin on 12.04.2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MicroclothesException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Void> processError(MicroclothesException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
