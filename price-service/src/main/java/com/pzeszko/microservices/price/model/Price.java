package com.pzeszko.microservices.price.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Admin on 07.04.2017.
 */
@Entity
@Table(name = "PRICE")
@Data
public class Price {

    @Id
    private Long id;

    @Column(name = "ITEM_ID", nullable = false)
    private String itemId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency = "PLN";
}
