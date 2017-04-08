package com.pzeszko.microclothes.stock.service;

import com.pzeszko.microclothes.stock.model.StockItem;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
public interface StockItemService {
    List<StockItem> findAll();
}
