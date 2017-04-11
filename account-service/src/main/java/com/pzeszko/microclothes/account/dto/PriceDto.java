package com.pzeszko.microclothes.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Admin on 11.04.2017.
 */
@Data
@AllArgsConstructor
public class PriceDto {
    private BigDecimal amount;
    private String currency;
}
