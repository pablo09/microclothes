package com.pzeszko.exception;

/**
 * Created by Admin on 26.04.2017.
 */
public class UaaException extends RuntimeException {

    public UaaException(ErrorCode errorCode) {
        super(errorCode.getName());
    }
}
