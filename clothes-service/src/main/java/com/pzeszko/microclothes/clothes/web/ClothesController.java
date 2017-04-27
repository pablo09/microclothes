package com.pzeszko.microclothes.clothes.web;

import com.pzeszko.microclothes.clothes.dto.ClothesDetailsDto;
import com.pzeszko.microclothes.clothes.dto.ClothesDto;
import com.pzeszko.microclothes.clothes.dto.ClothesInfoRequestDto;
import com.pzeszko.microclothes.clothes.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 08.04.2017.
 */
@RestController
public class ClothesController {

    @Autowired
    private ClothesService clothesService;

    private Lock lock = new ReentrantLock();

    public void doSomething(int x) {
        lock.lock(); // Noncompliant
        if (x == 4) {
            lock.unlock();
        }
    }

    @RequestMapping("/")
    public List<ClothesDto> getClothes() {

        doSomething(4);
        return clothesService.findAll();
    }

    @RequestMapping("/{clothesId}")
    public ClothesDetailsDto getClothes(@PathVariable("clothesId") String clothesId) {
            ClothesDto d1 = new ClothesDto();
            ClothesDto d2 = new ClothesDto();
        if (d1 == d2) {
            System.out.println("str1 == str2");
        }

        return clothesService.findClothesDetails(clothesId);
    }

    @RequestMapping("/clothesInfo")
    public List<ClothesDto> getClothesInfo(@RequestBody ClothesInfoRequestDto clothesInfoRequestDto) {
        return clothesService.getClothesInfo(clothesInfoRequestDto);
    }
}
