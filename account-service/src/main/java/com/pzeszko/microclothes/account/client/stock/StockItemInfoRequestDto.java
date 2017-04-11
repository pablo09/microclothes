package com.pzeszko.microclothes.stock.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
public class StockItemInfoRequestDto {
    private List<Long> ids;
}
