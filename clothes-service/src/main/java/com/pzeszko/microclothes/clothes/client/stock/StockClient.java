package com.pzeszko.microclothes.clothes.client.stock;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
@FeignClient("stock-service")
public interface StockClient {

    @RequestMapping("/{itemId}")
    List<StockItemSpecimen> getAllItemsSpeciman(@PathVariable("itemId") String itemId);
}
