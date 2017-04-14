package com.pzeszko.microclothes.shoes.client.image;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 12.04.2017.
 */
@org.springframework.stereotype.Component
public class ImageClientFallback implements ImageClient{


    @Override
    public List<ImageDto> getImages() {
        return Collections.emptyList();
    }

    @Override
    public ImageDto getImageForShoe(@PathVariable("itemId") String itemId) {
        return null;
    }
}
