package com.pzeszko.microclothes.clothes.client.price;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@FeignClient("price-service")
public interface PriceClient {

    @RequestMapping("/")
    List<PriceDto> getAllPrices();

    @RequestMapping("/{itemId}")
    PriceDto getPriceForShoe(@PathVariable("itemId") String itemId);
}
