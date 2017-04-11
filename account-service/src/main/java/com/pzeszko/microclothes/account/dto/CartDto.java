package com.pzeszko.microclothes.account.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
public class CartDto {
    private List<ItemInfoDto> items;
}
