package com.pzeszko.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Admin on 26.04.2017.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INCORRECT_PASSWORD("Given password was incorrect"),
    TOKEN_NOT_FOUND("Given token has not been found"),
    TOKEN_EXPIRED("Given token has expired");

    private String name;
}
