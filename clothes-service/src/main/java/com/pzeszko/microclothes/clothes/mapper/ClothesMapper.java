package com.pzeszko.microclothes.clothes.mapper;

import com.pzeszko.microclothes.clothes.client.image.ImageDto;
import com.pzeszko.microclothes.clothes.client.price.PriceDto;
import com.pzeszko.microclothes.clothes.dto.ClothesDto;
import com.pzeszko.microclothes.clothes.dto.Price;
import com.pzeszko.microclothes.clothes.model.Clothes;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 08.04.2017.
 */
@Service
public class ClothesMapper {

    public ClothesDto map(Clothes clothes, ImageDto image, PriceDto price) {
        ClothesDto dto = new ClothesDto();

        dto.setId(clothes.getId());
        dto.setBrand(clothes.getBrand());
        dto.setName(clothes.getName());

        addImageInfo(image, dto);
        addPriceInfo(price, dto);

        return dto;
    }

    private void addImageInfo(ImageDto image, ClothesDto dto) {
        if(image != null) {
            dto.setImage(image.getContent());
        }
    }

    private void addPriceInfo(PriceDto price, ClothesDto dto) {
        if(price != null) {
            dto.setPrice(new Price(price.getAmount(), price.getCurrency()));
        }
    }
}
