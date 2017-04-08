package com.pzeszko.microclothes.shoes.dto;

import lombok.Data;

import java.util.Map;

/**
 * Created by Admin on 07.04.2017.
 */
@Data
public class ShoeDetailsDto {
    private String id;
    private String name;
    private String brand;
    private String image;
    private String category;
    private String description;
    private Price price;
    private Map<String,  Map<String, Integer>> specimens;
}
