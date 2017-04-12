package com.pzeszko.microclothes.order.client.shoes;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@FeignClient("shoes-service")
public interface ShoesClient {

    @RequestMapping("/")
    List<ShoesDto> getShoes();

    @RequestMapping("/shoesInfo")
    public List<ShoesDto> getShoesInfo(@RequestBody ShoesInfoRequestDto requestDto);
}
