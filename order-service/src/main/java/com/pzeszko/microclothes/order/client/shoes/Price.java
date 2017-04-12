package com.pzeszko.microclothes.order.client.shoes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Admin on 07.04.2017.
 */
@Data
@AllArgsConstructor
public class Price {
    private BigDecimal amount;
    private String currency;
}
