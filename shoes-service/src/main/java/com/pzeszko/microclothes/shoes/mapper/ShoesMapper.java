package com.pzeszko.microclothes.shoes.mapper;

import com.pzeszko.microclothes.shoes.client.image.ImageDto;
import com.pzeszko.microclothes.shoes.client.price.PriceDto;
import com.pzeszko.microclothes.shoes.client.stock.StockItemSpecimen;
import com.pzeszko.microclothes.shoes.dto.Price;
import com.pzeszko.microclothes.shoes.dto.ShoeDetailsDto;
import com.pzeszko.microclothes.shoes.dto.ShoesDto;
import com.pzeszko.microclothes.shoes.model.Shoes;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Admin on 07.04.2017.
 */
@Service
public class ShoesMapper {


    public ShoesDto map(Shoes shoes, ImageDto image, PriceDto price) {
        ShoesDto dto = new ShoesDto();

        dto.setId(shoes.getId());
        dto.setName(shoes.getName());
        dto.setBrand(shoes.getBrand());

        if(image != null) {
            dto.setImage(image.getContent());
        }

        if(price != null) {
            dto.setPrice(new Price(price.getAmount(), price.getCurrency()));
        }

        return dto;
    }

    public ShoeDetailsDto mapDetails(Shoes shoes, ImageDto image, PriceDto price, List<StockItemSpecimen> specimens) {
        ShoeDetailsDto dto = new ShoeDetailsDto();

        dto.setId(shoes.getId());
        dto.setName(shoes.getName());
        dto.setBrand(shoes.getBrand());
        dto.setDescription(shoes.getDescription());
        dto.setCategory(shoes.getCategory().name());

        addImageInfo(image, dto);
        addPriceInfo(price, dto);
        addSpecimensInfo(specimens, dto);


        return dto;
    }

    private void addSpecimensInfo(List<StockItemSpecimen> specimens, ShoeDetailsDto dto) {
        if(specimens != null) {
            Map<String, Map<String, Integer>> specimenInfoMap = new HashMap<>();
            Set<String> availableColors = specimens.stream().map(s -> s.getColor()).collect(toSet());
            setSizeAmountForAllColors(specimens, specimenInfoMap, availableColors);

            dto.setSpecimens(specimenInfoMap);
        }
    }

    private void setSizeAmountForAllColors(List<StockItemSpecimen> specimens, Map<String, Map<String, Integer>> specimenInfoMap, Set<String> availableColors) {
        for(String color: availableColors) {
            Map<String, Integer> sizeAmountMap = new HashMap<>();

            specimens.stream().filter(s -> s.getColor().equals(color)).forEach(s -> {
                sizeAmountMap.put(s.getSize(), s.getAmount());
            });

            specimenInfoMap.put(color, sizeAmountMap);
        }
    }

    private void addPriceInfo(PriceDto price, ShoeDetailsDto dto) {
        if(price != null) {
            dto.setPrice(new Price(price.getAmount(), price.getCurrency()));
        }
    }

    private void addImageInfo(ImageDto image, ShoeDetailsDto dto) {
        if(image != null) {
            dto.setImage(image.getContent());
        }
    }


}
