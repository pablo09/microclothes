package com.pzeszko.microclothes.shoes.dto;

import com.pzeszko.microclothes.shoes.client.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Admin on 07.04.2017.
 */
@Data
@AllArgsConstructor
public class Shoes {
    private Long id;
    private String name;
    private ImageDto image;
}
