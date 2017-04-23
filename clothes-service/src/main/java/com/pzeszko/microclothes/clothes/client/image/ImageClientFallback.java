package com.pzeszko.microclothes.clothes.client.image;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 12.04.2017.
 */
@org.springframework.stereotype.Component
@Log4j
public class ImageClientFallback implements ImageClient{


    @Override
    public List<ImageDto> getImages() {
        log.warn("Using fallback method for ImageClient#getImages");
        return Collections.emptyList();
    }

    @Override
    public ImageDto getImageForShoe(@PathVariable("itemId") String itemId) {
        return null;
    }
}
