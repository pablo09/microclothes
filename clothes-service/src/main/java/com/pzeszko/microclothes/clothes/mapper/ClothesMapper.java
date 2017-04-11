package com.pzeszko.microclothes.clothes.mapper;

import com.pzeszko.microclothes.clothes.client.image.ImageDto;
import com.pzeszko.microclothes.clothes.client.price.PriceDto;
import com.pzeszko.microclothes.clothes.client.stock.StockItemSpecimen;
import com.pzeszko.microclothes.clothes.dto.ClothesDetailsDto;
import com.pzeszko.microclothes.clothes.dto.ClothesDto;
import com.pzeszko.microclothes.clothes.dto.Price;
import com.pzeszko.microclothes.clothes.dto.SpecimenInfo;
import com.pzeszko.microclothes.clothes.model.Clothes;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

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

    public ClothesDetailsDto mapDetails(Clothes clothes, ImageDto image, PriceDto price, List<StockItemSpecimen> specimens) {
        ClothesDetailsDto dto = new ClothesDetailsDto();

        dto.setId(clothes.getId());
        dto.setBrand(clothes.getBrand());
        dto.setName(clothes.getName());
        dto.setDescription(clothes.getDescription());

        addImageInfo(image, dto);
        addPriceInfo(price, dto);
        addSpecimensInfo(specimens, dto);

        return dto;
    }

    private void addSpecimensInfo(List<StockItemSpecimen> specimens, ClothesDetailsDto dto) {
        if(specimens != null) {
            Map<String, Map<String, SpecimenInfo>> specimenInfoMap = new HashMap<>();
            Set<String> availableColors = specimens.stream().map(s -> s.getColor()).collect(toSet());
            setSizeAmountForAllColors(specimens, specimenInfoMap, availableColors);

            dto.setSpecimens(specimenInfoMap);
        }
    }

    private void setSizeAmountForAllColors(List<StockItemSpecimen> specimens, Map<String, Map<String, SpecimenInfo>> specimenInfoMap, Set<String> availableColors) {
        for(String color: availableColors) {
            Map<String, SpecimenInfo> sizeAmountMap = new HashMap<>();

            specimens.stream().filter(s -> s.getColor().equals(color)).forEach(s -> {
                sizeAmountMap.put(s.getSize(), new SpecimenInfo(s.getItemId(), s.getAmount()));
            });

            specimenInfoMap.put(color, sizeAmountMap);
        }
    }

    private void addImageInfo(ImageDto image, ClothesDto dto) {
        if(image != null) {
            dto.setImage(image.getContent());
        }
    }

    private void addImageInfo(ImageDto image, ClothesDetailsDto dto) {
        if(image != null) {
            dto.setImage(image.getContent());
        }
    }


    private void addPriceInfo(PriceDto price, ClothesDto dto) {
        if(price != null) {
            dto.setPrice(new Price(price.getAmount(), price.getCurrency()));
        }
    }

    private void addPriceInfo(PriceDto price, ClothesDetailsDto dto) {
        if(price != null) {
            dto.setPrice(new Price(price.getAmount(), price.getCurrency()));
        }
    }
}
