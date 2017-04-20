package com.pzeszko.microclothes.order.client.shoes;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 19.04.2017.
 */
@Component
public class ShoesClientFallback implements ShoesClient {
    @Override
    public List<ShoesDto> getShoes() {
        return Collections.emptyList();
    }

    @Override
    public List<ShoesDto> getShoesInfo(@RequestBody ShoesInfoRequestDto requestDto) {
        return Collections.emptyList();
    }
}
