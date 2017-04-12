package com.pzeszko.microclothes.order.dto;

import com.pzeszko.microclothes.order.client.price.PriceDto;
import lombok.Data;

/**
 * Created by Admin on 12.04.2017.
 */
@Data
public class ItemDto {

    private String name;
    private String size;
    private String color;
    private PriceDto price;
}
