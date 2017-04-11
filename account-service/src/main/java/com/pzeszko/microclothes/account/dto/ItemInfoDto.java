package com.pzeszko.microclothes.account.dto;

import lombok.Data;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
public class ItemInfoDto {
    private Long specimenId;
    private String name;
    private String size;
    private String color;
    private PriceDto price;
}
