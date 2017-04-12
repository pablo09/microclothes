package com.pzeszko.microclothes.order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by Admin on 11.04.2017.
 */
@Embeddable
@Data
public class Price {

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency;
}
