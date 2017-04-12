package com.pzeszko.microclothes.order.client.clothes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@Data
@AllArgsConstructor
public class ClothesInfoRequestDto {
    private List<String> ids;
}
