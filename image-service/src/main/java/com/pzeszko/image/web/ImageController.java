package com.pzeszko.image.web;

import com.pzeszko.image.dto.ImageDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@RestController
public class ImageController {

    @RequestMapping("/")
    public List<ImageDto> images() {
        return Arrays.asList(
                new ImageDto("nike.png", "nike"),
                new ImageDto("adidas.png", "adidas")
        );
    }

}
