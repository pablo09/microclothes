package com.pzeszko.microclothes.order.client.clothes;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 19.04.2017.
 */
@Component
public class ClothesClientFallback implements ClothesClient{

    @Override
    public List<ClothesDto> getClothesInfo(@RequestBody ClothesInfoRequestDto clothesInfoRequestDto) {
        return Collections.emptyList();
    }
}
