package com.pzeszko.microclothes.shoes.service;

import com.pzeszko.microclothes.shoes.dto.ShoeDetailsDto;
import com.pzeszko.microclothes.shoes.dto.ShoesDto;
import com.pzeszko.microclothes.shoes.model.Shoes;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
public interface ShoesService {

    List<Shoes> findAll();

    List<ShoesDto> findAllShoesDtos();

    ShoeDetailsDto findShoe(String shoeId);
}
