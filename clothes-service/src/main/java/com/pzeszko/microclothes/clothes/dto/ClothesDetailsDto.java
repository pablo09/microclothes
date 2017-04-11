package com.pzeszko.microclothes.clothes.dto;

import lombok.Data;

import java.util.Map;

/**
 * Created by Admin on 09.04.2017.
 */
@Data
public class ClothesDetailsDto {
    private String id;
    private String name;
    private String brand;
    private String image;
    private Price price;
    private String description;
    private Map<String, Map<String, SpecimenInfo>> specimens;

}
