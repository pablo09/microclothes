package com.pzeszko.microclothes.stock.service;

import com.pzeszko.microclothes.stock.dto.StockItemDto;
import com.pzeszko.microclothes.stock.dto.StockItemInfoRequestDto;
import com.pzeszko.microclothes.stock.dto.StockItemSpecimenDto;
import com.pzeszko.microclothes.stock.model.StockItemSpecimen;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
public interface StockItemSpecimenService {

    List<StockItemSpecimen> findAllStockItemSpecimens();

    List<StockItemSpecimen> findByItemId(String itemId);

    List<StockItemSpecimenDto> findStockDtosByItemId(String itemId);

    List<StockItemSpecimenDto> findStockItemSpecimensByIds(StockItemInfoRequestDto request);

    List<StockItemDto> getItemIdsForStockItems(StockItemInfoRequestDto request);

    void buyItems(StockItemInfoRequestDto request);
}
