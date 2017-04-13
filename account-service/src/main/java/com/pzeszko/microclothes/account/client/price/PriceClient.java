package com.pzeszko.microclothes.account.client.price;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@FeignClient(name = "price-service", fallback = PriceClientFallback.class)
public interface PriceClient {

    @RequestMapping("/")
    List<PriceDto> getAllPrices();

    @RequestMapping("/{itemId}")
    PriceDto getPriceForShoe(@PathVariable("itemId") String itemId);

    @RequestMapping("/prices")
    List<PriceDto> getPriceForRequestedItems(@RequestBody PricesRequestDto pricesDto);
}
