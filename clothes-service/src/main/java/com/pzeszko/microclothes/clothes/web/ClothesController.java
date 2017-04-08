package com.pzeszko.microclothes.clothes.web;

import com.pzeszko.microclothes.clothes.dto.ClothesDto;
import com.pzeszko.microclothes.clothes.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
@RestController
public class ClothesController {

    @Autowired
    private ClothesService clothesService;

    @RequestMapping("/")
    public List<ClothesDto> getClothes() {
        return clothesService.findAll();
    }
}
