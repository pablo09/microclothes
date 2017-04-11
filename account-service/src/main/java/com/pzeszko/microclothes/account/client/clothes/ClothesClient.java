package com.pzeszko.microclothes.account.client.clothes;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@FeignClient("clothes-service")
public interface ClothesClient {

    @RequestMapping("/clothesInfo")
    List<ClothesDto> getClothesInfo(@RequestBody ClothesInfoRequestDto clothesInfoRequestDto);
}
