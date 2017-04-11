package com.pzeszko.microclothes.clothes.client.stock;

import lombok.Data;

/**
 * Created by Admin on 08.04.2017.
 */
@Data
public class StockItemSpecimen {

    private Long itemId;

    private String color;

    private String type;

    private String size;

    private Integer amount;
}
