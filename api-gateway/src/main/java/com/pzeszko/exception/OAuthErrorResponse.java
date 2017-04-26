package com.pzeszko.exception;

import lombok.Data;

/**
 * Created by Admin on 26.04.2017.
 */
@Data
public class OAuthErrorResponse {
    private String errorCode;
    private String errorDescription;

    public OAuthErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.name();
        this.errorDescription = errorCode.getDescription();
    }
}
