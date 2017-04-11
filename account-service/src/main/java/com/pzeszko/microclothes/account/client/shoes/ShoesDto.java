package com.pzeszko.microclothes.account.client.shoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Admin on 07.04.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoesDto {
    private String id;
    private String name;
    private String brand;
    private String image;
    private Price price;
}
