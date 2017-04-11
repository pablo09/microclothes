package com.pzeszko.microclothes.account.model;

import com.pzeszko.microclothes.account.model.converter.ListToStringConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 09.04.2017.
 */
@Entity
@Table(name = "CART")
@Data
public class Cart {

    @Id
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "STOCK_ITEM_IDS")
    @Convert(converter = ListToStringConverter.class)
    private List<Long> stockItemIds;
}
