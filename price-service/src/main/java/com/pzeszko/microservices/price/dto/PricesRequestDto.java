package com.pzeszko.microservices.price.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 10.04.2017.
 */
@Data
public class PricesRequestDto {
    private List<String> ids;
}
