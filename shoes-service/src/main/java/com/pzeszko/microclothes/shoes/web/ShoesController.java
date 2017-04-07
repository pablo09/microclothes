package com.pzeszko.microclothes.shoes.web;

import com.pzeszko.microclothes.shoes.client.ImageClient;
import com.pzeszko.microclothes.shoes.client.ImageDto;
import com.pzeszko.microclothes.shoes.dto.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@RestController
public class ShoesController {

    @Autowired
    private ImageClient imageClient;

    @RequestMapping("/")
    public List<Shoes> getShoes() {
        List<ImageDto> images = imageClient.getImages();
        return Arrays.asList(
                new Shoes(1L, "Nike", images.get(0)),
                new Shoes(2L,  "Adidas", images.get(1))
        );
    }
}
