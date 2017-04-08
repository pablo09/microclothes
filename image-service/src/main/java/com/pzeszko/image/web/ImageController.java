package com.pzeszko.image.web;

import com.pzeszko.image.dto.ImageDto;
import com.pzeszko.image.service.ImageLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@RestController
public class ImageController {

    @Autowired
    private ImageLoader imageLoader;

    @RequestMapping("/")
    public List<ImageDto> images() throws IOException {
        return imageLoader.loadImages();
    }

    @RequestMapping("/{itemId}")
    public ImageDto imageForShoe(@PathVariable("itemId") String itemId) throws IOException {
        //TODO
        return imageLoader.loadImages().stream().filter(image -> image.getItemId().equals(itemId)).findFirst().get();
    }

}
