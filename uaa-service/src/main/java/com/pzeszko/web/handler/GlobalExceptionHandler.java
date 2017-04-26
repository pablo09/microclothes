package com.pzeszko.web.handler;

import com.pzeszko.exception.UaaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Admin on 26.04.2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UaaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Void> processValidationError(UaaException ex) {
        return ResponseEntity.status(500).build();
    }
}
