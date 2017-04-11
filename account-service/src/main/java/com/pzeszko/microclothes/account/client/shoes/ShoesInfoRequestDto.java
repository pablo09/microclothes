package com.pzeszko.microclothes.account.client.shoes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@Data
@AllArgsConstructor
public class ShoesInfoRequestDto {
    private List<String> ids;
}
