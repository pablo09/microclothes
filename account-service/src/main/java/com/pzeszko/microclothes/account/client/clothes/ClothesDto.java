package com.pzeszko.microclothes.account.client.clothes;

import com.pzeszko.microclothes.account.client.shoes.Price;
import lombok.Data;

/**
 * Created by Admin on 08.04.2017.
 */
@Data
public class ClothesDto {
    private String id;
    private String name;
    private String brand;
    private String image;
    private Price price;
}
