package com.pzeszko.microclothes.stock.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Admin on 08.04.2017.
 */

@Entity
@Table(name = "STOCK_ITEM")
@Data
public class StockItem {

    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "ITEM_ID", nullable = false)
    private String itemId;

    @Column(name = "COLOR", nullable = false)
    private String  color;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType type;


}
