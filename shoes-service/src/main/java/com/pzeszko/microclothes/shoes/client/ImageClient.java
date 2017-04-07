package com.pzeszko.microclothes.shoes.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@FeignClient("image-service")
public interface ImageClient {

    @RequestMapping(value = "/")
    List<ImageDto> getImages();
}