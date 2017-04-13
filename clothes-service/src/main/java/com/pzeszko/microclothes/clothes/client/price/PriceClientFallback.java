package com.pzeszko.microclothes.clothes.client.price;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 12.04.2017.
 */
@Component
public class PriceClientFallback implements PriceClient {
    @Override
    public List<PriceDto> getAllPrices() {
        return Collections.emptyList();
    }

    @Override
    public PriceDto getPriceForShoe(@PathVariable("itemId") String itemId) {
        return createPriceNotKnownDto();
    }

    private PriceDto createPriceNotKnownDto() {
        PriceDto dto = new PriceDto();

        dto.setAmount(null);
        dto.setCurrency("UNKNOWN");
        return dto;
    }
}
