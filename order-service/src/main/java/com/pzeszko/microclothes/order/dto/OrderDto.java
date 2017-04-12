package com.pzeszko.microclothes.order.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@Data
public class OrderDto {
    @Size(min = 1)
    private List<Long> specimenIds;


}
