package com.pzeszko.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Admin on 26.04.2017.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("The request is missing a required parameter"),
    UNAUTHORIZED_CLIENT("The client is not authorized"),
    ACCESS_DENIED("Access to the resource has been denied"),
    INVALID_SCOPE("Given scope is invalid"),
    SERVER_ERROR("Internal server error occured");

    private String description;
}
