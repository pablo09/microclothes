package com.pzeszko.microclothes.order.client.stock;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
@FeignClient("stock-service")
public interface StockClient {

    @RequestMapping("/{itemId}")
    List<StockItemSpecimen> getAllItemsSpeciman(@PathVariable("itemId") String itemId);

    @RequestMapping("/specimens")
    List<StockItemSpecimen> getAllRequestedSpecimens(@RequestBody StockItemInfoRequestDto request);

    @RequestMapping("/getItemIds")
    List<StockItemDto> getItemIds(@RequestBody StockItemInfoRequestDto request);

    @RequestMapping(value = "/buyItem", method = RequestMethod.POST)
    ResponseEntity<Void> buyItem(@RequestBody StockItemInfoRequestDto request);
}
