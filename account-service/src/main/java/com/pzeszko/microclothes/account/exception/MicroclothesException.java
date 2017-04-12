package com.pzeszko.microclothes.account.exception;

import lombok.Getter;

/**
 * Created by Admin on 12.04.2017.
 */

@Getter
public class MicroclothesException extends RuntimeException {

    private final ErrorCode code;

    public MicroclothesException(ErrorCode code) {
        super(code.getCode());
        this.code = code;
    }
}

