package com.pzeszko.microservices.price.web;

import com.pzeszko.microservices.price.dto.PricesRequestDto;
import com.pzeszko.microservices.price.model.Price;
import com.pzeszko.microservices.price.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@RestController
public class PriceServiceController {

    @Autowired
    private PriceService priceService;

    @RequestMapping("/")
    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    @RequestMapping("/{itemId}")
    public Price getPriceForShoe(@PathVariable("itemId") String itemId) {
        return priceService.getPrice(itemId);
    }

    @RequestMapping("/prices")
    public List<Price> getPriceForRequestedItems(@RequestBody PricesRequestDto pricesDto) {
        return priceService.getPrices(pricesDto);
    }
}
