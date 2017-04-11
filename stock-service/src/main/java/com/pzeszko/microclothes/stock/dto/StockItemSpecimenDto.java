package com.pzeszko.microclothes.stock.dto;

import lombok.Data;

/**
 * Created by Admin on 08.04.2017.
 */
@Data
public class StockItemSpecimenDto {
    private Long itemId;

    private String item;

    private String color;

    private String type;

    private String size;

    private Integer amount;
}
