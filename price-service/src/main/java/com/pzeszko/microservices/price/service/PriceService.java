package com.pzeszko.microservices.price.service;

import com.pzeszko.microservices.price.model.Price;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */

public interface PriceService {
    List<Price> getAllPrices();

    Price getPrice(String shoe);
}
