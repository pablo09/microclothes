package com.pzeszko.microclothes.stock.web;

import com.pzeszko.microclothes.stock.dto.StockItemDto;
import com.pzeszko.microclothes.stock.dto.StockItemInfoRequestDto;
import com.pzeszko.microclothes.stock.dto.StockItemSpecimenDto;
import com.pzeszko.microclothes.stock.service.StockItemSpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/specimens")
    public List<StockItemSpecimenDto> getAllRequestedSpecimens(@RequestBody StockItemInfoRequestDto request) {
        return stockItemSpecimenService.findStockItemSpecimensByIds(request);
    }

    @RequestMapping("/getItemIds")
    public List<StockItemDto> getItemIds(@RequestBody StockItemInfoRequestDto request) {
        return stockItemSpecimenService.getItemIdsForStockItems(request);
    }

    @RequestMapping(value = "/buyItem", method = RequestMethod.POST)
    public ResponseEntity<Void> buyItem(@RequestBody StockItemInfoRequestDto request) {
        stockItemSpecimenService.buyItems(request);
        return ResponseEntity.ok().build();
    }

}
