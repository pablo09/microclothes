package com.pzeszko.microclothes.stock.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Admin on 12.04.2017.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND("NOT_FOUND"),
    ITEMS_NOT_AVAILABLE("ITEMS_NOT_AVAILABLE");

    private final String code;
}
