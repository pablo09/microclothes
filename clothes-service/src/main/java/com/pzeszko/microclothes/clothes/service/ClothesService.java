package com.pzeszko.microclothes.clothes.service;

import com.pzeszko.microclothes.clothes.dto.ClothesDto;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
public interface ClothesService {
    List<ClothesDto> findAll();

    ClothesDto findOne(String itemId);
}
