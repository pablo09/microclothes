package com.pzeszko.microclothes.shoes.web;

import com.pzeszko.microclothes.shoes.dto.ShoeDetailsDto;
import com.pzeszko.microclothes.shoes.dto.ShoesDto;
import com.pzeszko.microclothes.shoes.service.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@RestController
public class ShoesController {

    @Autowired
    private ShoesService shoesService;

    @RequestMapping("/")
    public List<ShoesDto> getShoes() {
       return shoesService.findAllShoesDtos();
    }

    @RequestMapping("/{shoeId}")
    public ShoeDetailsDto getShoe(@PathVariable("shoeId") String shoeId) {
        return shoesService.findShoe(shoeId);
    }
}
