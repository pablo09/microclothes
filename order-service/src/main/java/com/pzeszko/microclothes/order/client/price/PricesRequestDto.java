package com.pzeszko.microclothes.order.client.price;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
@AllArgsConstructor
public class PricesRequestDto {
    private List<String> ids;
}
