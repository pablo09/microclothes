package com.pzeszko.microclothes.account.client.price;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    public List<PriceDto> getPriceForRequestedItems(@RequestBody PricesRequestDto pricesDto) {
        return Collections.emptyList();
    }

    private PriceDto createPriceNotKnownDto() {
        PriceDto dto = new PriceDto();

        dto.setAmount(null);
        dto.setCurrency("UNKNOWN");
        return dto;
    }
}
