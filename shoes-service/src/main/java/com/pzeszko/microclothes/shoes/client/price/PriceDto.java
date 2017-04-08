package com.pzeszko.microclothes.shoes.client.price;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Admin on 07.04.2017.
 */
@Data
public class PriceDto {
    private String itemId;
    private BigDecimal amount;
    private String currency;
}
