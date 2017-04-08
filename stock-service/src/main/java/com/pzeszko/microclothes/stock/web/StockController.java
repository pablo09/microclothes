package com.pzeszko.microclothes.stock.web;

import com.pzeszko.microclothes.stock.dto.StockItemSpecimenDto;
import com.pzeszko.microclothes.stock.service.StockItemSpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@RestController
public class StockController {

    @Autowired
    private StockItemSpecimenService stockItemSpecimenService;

    @RequestMapping("/{itemId}")
    public List<StockItemSpecimenDto> getAllItemsSpeciman(@PathVariable("itemId") String itemId) {
        return stockItemSpecimenService.findStockDtosByItemId(itemId);
    }
}
