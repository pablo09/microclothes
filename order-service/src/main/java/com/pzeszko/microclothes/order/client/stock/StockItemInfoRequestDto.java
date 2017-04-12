package com.pzeszko.microclothes.order.client.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockItemInfoRequestDto {
    private List<Long> ids;
}
