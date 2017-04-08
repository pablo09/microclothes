package com.pzeszko.microclothes.stock.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Admin on 08.04.2017.
 */
@Entity
@Table(name = "STOCK_ITEM_SPECIMEN")
@Data
public class StockItemSpecimen {

    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "STOCK_ITEM_ID")
    private StockItem item;

    @Column(name = "SIZE", nullable = false)
    private String size;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;
}
