package com.pzeszko.microclothes.order.client.stock;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
@AllArgsConstructor
public class StockItemDto {
    private String itemId;
    private Long stockItemId;
}
